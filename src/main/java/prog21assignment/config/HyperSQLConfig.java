package prog21assignment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class HyperSQLConfig {

    private static final Logger log = LoggerFactory.getLogger(HyperSQLConfig.class);

    @Bean
    public DataSource source() {
        log.debug("Establishing connection to HyperSQL database");

        return DataSourceBuilder
                .create()
                .driverClassName("org.hsqldb.jdbc.JDBCDriver")
                .url("jdbc:hsqldb:file:database/database")
                .username("levi")
                .password("treehouse")
                .build();
    }
}
