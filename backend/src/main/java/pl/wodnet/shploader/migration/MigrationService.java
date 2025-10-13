package pl.wodnet.shploader.migration;

import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.util.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.views.ViewService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn("dataSource")
public class MigrationService {
    @Autowired
    private HikariDataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewService.class);

    @PostConstruct
    public void migrate(){
        LOGGER.info("Rozpoczeto wgrywanie skryptu migracji");
        for(Migration migration : getMigrations()){
            executeSQL(getSQLFile(migration.getSqlFile()), migration.getSeparator());
        }
    }

    private List<Migration> getMigrations(){
        List<Migration> list = new ArrayList<>();
        String separator = "\n;\n";
        list.add(new Migration("Migracja rzednych w armaturze ","/migrations/armatura.sql", separator));
        return list;
    }

    private String getSQLFile(String path) {
        //InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource(path).getInputStream();
            return IOUtils.toString(inputStream, Charset.defaultCharset()); //"UTF-8"
        } catch (IOException e) {
            LOGGER.error("Error converting stream to string", e);
            return null;
        }
    }

    public boolean executeSQL(String sqlStatement, String splitString) {
        sqlStatement = sqlStatement.replace("\r\n", "\n");
        String currentQuery = "";
        try {
            String[] queries = sqlStatement.split(splitString);
            for (String querie : queries) {
                currentQuery = querie;
                jdbcTemplate.execute(currentQuery.trim());
            }
            return true;
        } catch (DataAccessException e) {
            LOGGER.error("Nie wykonano SQL script: " + currentQuery, e);
            ExceptionUtils.rethrow(e);
            return false;
        }
    }
}
