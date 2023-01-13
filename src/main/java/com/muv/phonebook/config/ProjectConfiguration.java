package com.muv.phonebook.config;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan(basePackages = "java")
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class ProjectConfiguration {

    Environment environment;

    public ProjectConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("spring.datasource.driver-class-name")));
        managerDataSource.setUrl(Objects.requireNonNull(environment.getProperty("spring.datasource.url")));
        managerDataSource.setUsername(Objects.requireNonNull(environment.getProperty("spring.datasource.username")));
        managerDataSource.setPassword(Objects.requireNonNull(environment.getProperty("spring.datasource.password")));
        return managerDataSource;
    }

}
