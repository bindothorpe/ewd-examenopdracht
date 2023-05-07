package com.bindothorpe.ewd_examenopdracht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import service.BookService;
import service.BookServiceImpl;
import service.UserService;
import service.UserServiceImpl;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class EwdExamenopdrachtApplication implements WebMvcConfigurer{

    public static void main(String[] args) {
        SpringApplication.run(EwdExamenopdrachtApplication.class, args);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/books");
        registry.addViewController("/403").setViewName("403");
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @Bean
    BookService bookService() {
        return new BookServiceImpl();
    }
    @Bean
    UserService userService() {
        return new UserServiceImpl();
    }
}
