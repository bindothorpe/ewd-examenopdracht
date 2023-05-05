package com.bindothorpe.ewd_examenopdracht;

import domain.Book;
import repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataConfig implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) {
        bookRepository.save(new Book("De meeste mensen deugen", 10.5, "978-6-6259-6059-2", "De Correspondent B.V."));
        bookRepository.save(new Book("Verslaaft aan lievde", 14.99, "978-8-7371-7002-9", "De Correspondent B.V."));
        bookRepository.save(new Book("Jobless Reincarnation vol 14", 12.5, "978-3-3951-8880-5", "De Correspondent B.V."));
        bookRepository.save(new Book("Jobless Reincarnation vol 15", 13.8, "978-0-6368-0454-8", "De Correspondent B.V."));

    }
}
