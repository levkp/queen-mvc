package prog21assignment.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class HSQLDatabaseConfig {

    @Bean
    public DataSource source() {
        return DataSourceBuilder
                .create()
                .driverClassName("org.hsqldb.jdbcDriver")
                .url("jdbc:hsqldb:file:database")
                .username("levi")
                .password("treehouse")
                .build();
    }
}
