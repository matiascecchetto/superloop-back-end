package au.com.superloop.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class TodosApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TodosApplication.class, args);
    }
}
