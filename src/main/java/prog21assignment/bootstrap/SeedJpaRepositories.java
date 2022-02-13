package prog21assignment.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("jpa")
@Component
@DependsOn({"persistGenres", "queenData"})
public class SeedJpaRepositories implements CommandLineRunner {
    private final QueenData data;

    @Autowired
    public SeedJpaRepositories(QueenData data) {
        this.data = data;
    }

    @Override
    public void run(String... args) {
        data.seed();
    }
}
