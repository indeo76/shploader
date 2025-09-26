package pl.wodnet.shploader;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.wodnet.shploader", entityManagerFactoryRef = "localContainerEntityManagerFactoryBean")
@ComponentScan("pl.wodnet.shploader")
public class PersistanceJPAConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistanceJPAConfig.class);

    private static String jdbcUrlLocation = null;

    public static String getJDBCUrl() {
        return jdbcUrlLocation;
    }

    private static String jdbcDbPassword = null;

    public static String getJDBCPassword() {
        return jdbcDbPassword;
    }

    @Bean
    @DependsOn("dataSource")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    @DependsOn("dataSource")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

//    @Bean
//    public DataSource dataSource() {
//
//        Properties propFile = propertiesFile();
//        if (!propFile.getProperty("db.url", "").isEmpty()) {
//            HikariConfig config = new HikariConfig();
//            config.setDriverClassName(propFile.getProperty("db.driverclassname"));
//            config.setJdbcUrl(propFile.getProperty("db.url"));
//            config.setUsername(propFile.getProperty("db.username"));
//            config.setPassword(propFile.getProperty("db.password"));
//
//            config.addDataSourceProperty("autoReconnect", true);
//            config.setPoolName("HikariCpConnectionPool");
//            config.setMaximumPoolSize(128);//50
//            config.setMinimumIdle(2);
//            config.setIdleTimeout(120000);
//            config.setMaxLifetime(120000);
//            config.setConnectionTimeout(30000);
//            //setConnectionTimeout(30000);
//            return new HikariDataSource(config);
//        } else {
//            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//            EmbeddedDatabase db = builder
//                    .setType(EmbeddedDatabaseType.H2)
//                    .build();
//            return db;
//        }
//
//    }

    @Bean
    public DataSource dataSource() {

        Properties propFile = propertiesFile();
        if(System.getenv("db.url") != null){
            System.out.println("Czyta zmienne srodowiskowe");
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(System.getenv("db.driverclassname"));
            config.setJdbcUrl(System.getenv("db.url"));
            config.setUsername(System.getenv("db.username"));
            config.setPassword(System.getenv("db.password"));

            config.addDataSourceProperty("autoReconnect", true);
            config.addDataSourceProperty("rewriteBatchedStatements", true );
            config.addDataSourceProperty( "cachePrepStmts" , "true" );
            config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
            config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
            config.setPoolName("HikariCpConnectionPool");
            config.setMaximumPoolSize(60);//50
            config.setMinimumIdle(2);
            config.setIdleTimeout(120000);
            config.setMaxLifetime(120000);
            config.setConnectionTimeout(30000);
            //setConnectionTimeout(30000);
            return new HikariDataSource(config);
        }else if (!propFile.getProperty("db.url", "").isEmpty()) {
            System.out.println("Czyta plik properties");
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(propFile.getProperty("db.driverclassname"));
            config.setJdbcUrl(propFile.getProperty("db.url"));
            config.setUsername(propFile.getProperty("db.username"));
            config.setPassword(propFile.getProperty("db.password"));

            config.addDataSourceProperty("autoReconnect", true);
            config.setPoolName("HikariCpConnectionPool");
            config.setMaximumPoolSize(128);//50
            config.setMinimumIdle(2);
            config.setIdleTimeout(120000);
            config.setMaxLifetime(120000);
            config.setConnectionTimeout(30000);
            //setConnectionTimeout(30000);
            return new HikariDataSource(config);
        } else {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            EmbeddedDatabase db = builder
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
            return db;
        }

    }

    @Bean(name = "flyway")
    @DependsOn({"dataSource"})
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setBaselineOnMigrate(true);
        if (propertiesFile().getProperty("migration.enabled", "true").equalsIgnoreCase("true")) {
            flyway.setLocations(propertiesFile().getProperty("migration.path", "db/migration/h2"));
            flyway.migrate();
        } else {
            LOGGER.info("Migrations are disabled. Set 'migration.enabled' to 'true' in db.properties to enable them.");
        }
        return flyway;
    }

    @Bean
    @DependsOn({"dataSource", "flyway"})
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{
                "pl.wodnet.**.*"
        });
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("application.properties"));
        return propertySourcesPlaceholderConfigurer;
    }

    private Properties propertiesFile() {
        String dbFileName = "db.properties";
        Properties prop = new Properties();
        //Path filePath = Paths.get(System.getProperty("jboss.home.dir"), "standalone", "deployments", dbFileName);
        //Path filePath = Paths.get("target", dbFileName);
        Path filePath = Paths.get("classes", "notificationservice", dbFileName);
        if (!filePath.toFile().isFile()) {
            try {
                filePath = Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replaceFirst("^/(.:/)", "$1"), dbFileName);
            } catch (Exception ex) {
                LOGGER.error("Error loading config file: " + ex.getMessage());
            }
        }
        if (!filePath.toFile().isFile()) {
            filePath = Paths.get(filePath.toFile().getParentFile().getParent(), dbFileName);
        }
        LOGGER.info("Searching for config file in " + filePath.toAbsolutePath().toString());
        if (filePath.toFile().isFile()) {
            prop = setPropertiesFromPath(prop, filePath);
        } else {
            LOGGER.error("Can't find " + dbFileName + "! (starting default embedded database)");
        }

        return prop;
    }

    private Properties setPropertiesFromPath(Properties prop, Path path) {
        try {
            String fileContent = new String(Files.readAllBytes(path));
            fileContent = fileContent.replace("\\", "\\\\");
            prop.load(new StringReader(fileContent));
            jdbcUrlLocation = prop.getProperty("db.url");
            jdbcDbPassword = prop.getProperty("db.password");
        } catch (IOException ex) {
            LOGGER.error("Blad otwarcia pliku: " + path.toString(), ex);
        }
        return prop;
    }

//    private Properties additionalProperties() {
//        Properties propFile = propertiesFile();
//        Properties properties = new Properties();
//        if (!propFile.getProperty("db.url", "").isEmpty()) {
//            properties.setProperty("hibernate.hbm2ddl.auto", propFile.getProperty("hibernate.hbm2ddl.auto"));
//            properties.setProperty("hibernate.dialect", propFile.getProperty("hibernate.dialect"));
//            properties.setProperty("hibernate.show_sql", propFile.getProperty("hibernate.show_sql"));
//            properties.setProperty("hibernate.format_sql", propFile.getProperty("hibernate.format_sql"));
//            properties.setProperty("hibernate.use_sql_comments", "false");
//            properties.setProperty("hibernate.id.new_generator_mappings", "false");
//            properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
//            properties.setProperty("hibernate.generate_statistics", "false");
//        } else {
//            properties.setProperty("generateDdl", "true");
//            properties.setProperty("database", "H2");
//        }
//        return properties;
//
//    }

    private Properties additionalProperties() {
        Properties propFile = propertiesFile();
        Properties properties = new Properties();
        if(System.getenv("db.url") != null){
            properties.setProperty("hibernate.hbm2ddl.auto", System.getenv("hibernate.hbm2ddl.auto"));
            properties.setProperty("hibernate.dialect", System.getenv("hibernate.dialect"));
            properties.setProperty("hibernate.show_sql", System.getenv("hibernate.show_sql"));
            properties.setProperty("hibernate.format_sql", System.getenv("hibernate.format_sql"));
            properties.setProperty("hibernate.use_sql_comments", "false");
            properties.setProperty("hibernate.id.new_generator_mappings", "false");
            properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
            properties.setProperty("hibernate.generate_statistics", System.getenv("hibernate.generate_statistics"));
            //properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", System.getenv("hibernate.jdbc.lob.non_contextual_creation"));

        }else if (!propFile.getProperty("db.url", "").isEmpty()) {
            properties.setProperty("hibernate.hbm2ddl.auto", propFile.getProperty("hibernate.hbm2ddl.auto"));
            properties.setProperty("hibernate.dialect", propFile.getProperty("hibernate.dialect"));
            properties.setProperty("hibernate.show_sql", propFile.getProperty("hibernate.show_sql"));
            properties.setProperty("hibernate.format_sql", propFile.getProperty("hibernate.format_sql"));
            properties.setProperty("hibernate.use_sql_comments", "false");
            properties.setProperty("hibernate.id.new_generator_mappings", "false");
            properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
            properties.setProperty("hibernate.generate_statistics", propFile.getProperty("hibernate.generate_statistics"));
            //properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", propFile.getProperty("hibernate.jdbc.lob.non_contextual_creation"));
        } else {
            properties.setProperty("generateDdl", "true");
            properties.setProperty("database", "H2");
        }
        return properties;

    }
}
