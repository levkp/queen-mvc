package prog21assignment.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev2")
@Component
public class SeedCollectionsRepositories implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}
