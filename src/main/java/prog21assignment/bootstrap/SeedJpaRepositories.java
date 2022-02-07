package prog21assignment.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("jpa")
@Component
@DependsOn("persistGenres")
public class SeedJpaRepositories implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}
