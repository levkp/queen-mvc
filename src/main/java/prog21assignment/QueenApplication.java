package prog21assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import prog21assignment.presentation.View;

@SpringBootApplication
public class QueenApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(QueenApplication.class, args);
        View v = context.getBean(View.class);
        v.showMenu();
        context.close();
    }
}
