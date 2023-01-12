package com.muv.phonebook;

import com.muv.phonebook.config.ProjectConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class PhonebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhonebookApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
    }

}
