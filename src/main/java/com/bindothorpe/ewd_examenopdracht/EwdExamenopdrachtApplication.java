package com.bindothorpe.ewd_examenopdracht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import service.*;
import validator.BookRegistrationValidator;

import java.util.Locale;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class EwdExamenopdrachtApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(EwdExamenopdrachtApplication.class, args);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/books");
    }

    @Bean
    LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }


    @Bean
    BookService bookService() {
        return new BookServiceImpl();
    }

    @Bean
    AuthorService authorService() {
        return new AuthorServiceImpl();
    }

    @Bean
    UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    LocationService locationService() {
        return new LocationServiceImpl();
    }

    @Bean
    BookRegistrationValidator bookRegistrationValidator() {
        return new BookRegistrationValidator();
    }
}
