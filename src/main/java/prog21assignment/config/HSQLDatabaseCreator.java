package prog21assignment.config;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class HSQLDatabaseCreator {

    private JdbcTemplate template;



}
