package prog21assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import prog21assignment.presentation.ConsoleAppView;

@SpringBootApplication
public class QueenApplication {

    public static void main(String[] args) {

        // Web app
//        SpringApplication.run(QueenApplication.class, args);

        // Console app
        ConfigurableApplicationContext context = SpringApplication.run(QueenApplication.class, args);
        ConsoleAppView v = context.getBean(ConsoleAppView.class);
        v.showMenu();
        context.close();
    }
}
