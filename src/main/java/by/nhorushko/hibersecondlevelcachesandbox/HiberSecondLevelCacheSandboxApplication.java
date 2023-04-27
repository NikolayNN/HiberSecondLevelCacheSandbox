package by.nhorushko.hibersecondlevelcachesandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class HiberSecondLevelCacheSandboxApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(HiberSecondLevelCacheSandboxApplication.class, args);
        System.out.println(run.getBean(BookService.class).getById(1));
    }

}
