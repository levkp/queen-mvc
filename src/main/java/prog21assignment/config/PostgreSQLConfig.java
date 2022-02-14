package prog21assignment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Profile("prod")
@Configuration
public class PostgreSQLConfig {
    private final Environment env;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public PostgreSQLConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource source() {
        log.debug("Establishing connection to PostgreSQL database");

        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("spring.datasource.driver-class-name"))
                .url(env.getProperty("spring.datasource.url"))
                .username(env.getProperty("spring.datasource.username"))
                .password(env.getProperty("spring.datasource.password"))
                .build();
    }
}
