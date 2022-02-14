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

@Profile("dev")
@Configuration
public class HyperSQLConfig {
    private final Environment env;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public HyperSQLConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource source() {
        log.debug("Establishing connection to HyperSQL database");

        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("spring.datasource.driver-class-name"))
                .url(env.getProperty("spring.datasource.url"))
                .username(env.getProperty("spring.datasource.username"))
                .password(env.getProperty("spring.datasource.password"))
                .build();
    }
}
