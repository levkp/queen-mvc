package prog21assignment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class PostgreSQLConfig {
    private static final Logger log = LoggerFactory.getLogger(PostgreSQLConfig.class);

    @Bean
    public DataSource source() {
        log.debug("Establishing connection to PostgreSQL database");

        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql:queendb")
                .username("levi")
                .password("levi")
                .build();
    }
}
