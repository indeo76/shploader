package pl.wodnet.shploader.views;


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

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Service
@DependsOn("dataSource")
public class ViewService {
    @Autowired
    private HikariDataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewService.class);


//    @PostConstruct
    public void createViews() {

        LOGGER.info("Rozpoczeto wgrywanie skryptu modyfikującego widoki");
        executeSQL(getView("/views/mobile.co_armatura.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.co_sieci.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.co_obiekty.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.eng_armatura.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.eng_obiekty.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.eng_sieci.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.gaz_armatura.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.gaz_obiekty.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.gaz_sieci.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.kan_armatura_c.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.kan_armatura_n.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.kan_obiekty_c.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.kan_obiekty_n.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.kan_sieci_c.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.kan_sieci_n.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.wod_armatura_c.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.wod_armatura_n.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.wod_obiekty_c.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.wod_obiekty_n.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.wod_sieci_c.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.wod_sieci_n.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.spc_armatura.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.spc_obiekty.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.spc_sieci.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.tel_armatura.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.tel_obiekty.sql"), "\n;\n");
        executeSQL(getView("/views/mobile.tel_sieci.sql"), "\n;\n");

        LOGGER.info("Zakończono wgrywanie skryptu modyfikującego widoki");
    }

    private String getView(String path) {
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
