package com.bindothorpe.ewd_examenopdracht;

import domain.Author;
import domain.Book;
import domain.Location;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repository.AuthorRepository;
import repository.BookRepository;
import repository.LocationRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitDataConfig implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void run(String... args) {
        List<Book> books = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        List<User> users = new ArrayList<>();
        books.add(new Book("Verslaaft aan liefde", 14.992, "978-8-7371-7002-9".replaceAll("[^0-9]", ""), "https://media.s-bol.com/mZQwz57kMGRR/758x1200.jpg"));
        books.add(new Book("Jobless Reincarnation vol 14", 12.5, "978-3-3951-8880-5".replaceAll("[^0-9]", ""), "https://kbimages1-a.akamaihd.net/df1b57ad-23ea-4b47-8802-6fa15504537a/1200/1200/False/mushoku-tensei-jobless-reincarnation-light-novel-vol-14.jpg"));
        books.add(new Book("Jobless Reincarnation vol 15", 13.8, "978-0-6368-0454-8".replaceAll("[^0-9]", ""), "https://kbimages1-a.akamaihd.net/cc08905b-6d4a-4ab2-8ea4-cc2ea879abcf/1200/1200/False/mushoku-tensei-jobless-reincarnation-light-novel-vol-15.jpg"));
        books.add(new Book("De meeste mensen deugen", 10.5, "978-6-6259-6059-2".replaceAll("[^0-9]", ""), "https://media.s-bol.com/NkD9WD1mzx48/798x1200.jpg"));

        Location loc = new Location(50, 100, "Gent");

        User admin = new User("nameAdmin", 5);
        users.add(new User("nameUser", 3));
        users.add(admin);

        authors.add(new Author("Rudeus"));
        authors.add(new Author("Bindo"));
        authors.add(new Author("Audrey"));

        for (Book book : books) {

            for (Author author : authors) {
                author.getBookList().add(book);
                book.getAuthors().add(author);
            }

            for (User user : users) {
                if (user.getUsername().equals("nameAdmin"))
                    continue;

                if(book.getTitle().equals("Verslaaft aan liefde"))
                    continue;
                user.getBookList().add(book);
                book.getUsersList().add(user);
            }

        }

        admin.getBookList().add(books.get(0));
        books.get(0).getUsersList().add(admin);
        books.get(0).getLocations().add(loc);

        bookRepository.saveAll(books);
        userRepository.saveAll(users);
        authorRepository.saveAll(authors);
        locationRepository.save(loc);


    }
}
