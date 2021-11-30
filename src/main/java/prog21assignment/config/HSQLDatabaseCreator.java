/*
package prog21assignment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class HSQLDatabaseCreator {

    private JdbcTemplate template;

    private static final Logger log = LoggerFactory.getLogger(HSQLDatabaseCreator.class);

    public HSQLDatabaseCreator(JdbcTemplate template) {
        this.template = template;
    }

    @PostConstruct
    public void loadData(){

        log.info("Loading data");

        template.update("DROP TABLE IF EXISTS PERSONS");
        template.update("CREATE TABLE PERSONS(ID INTEGER NOT NULL IDENTITY, " +
                "NAME VARCHAR(100) NOT NULL, FIRSTNAME VARCHAR(100) NOT NULL)");
        template.update("INSERT INTO PERSONS(NAME, FIRSTNAME) " +
                "VALUES ('HSQLJONES', 'JACK'), ('POTTER', 'JACK'), ('POTTER', 'MIA'), ('REED', 'JACK')");
    }
}
*/
