package com.bindothorpe.ewd_examenopdracht;

import domain.Author;
import domain.Book;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repository.AuthorRepository;
import repository.BookRepository;
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

    @Override
    public void run(String... args) {
        List<Book> books = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        List<User> users = new ArrayList<>();
        books.add(new Book("Verslaaft aan liefde", 14.99, "978-8-7371-7002-9", "https://media.s-bol.com/mZQwz57kMGRR/758x1200.jpg"));
        books.add(new Book("Jobless Reincarnation vol 14", 12.5, "978-3-3951-8880-5", "https://kbimages1-a.akamaihd.net/df1b57ad-23ea-4b47-8802-6fa15504537a/1200/1200/False/mushoku-tensei-jobless-reincarnation-light-novel-vol-14.jpg"));
        books.add(new Book("Jobless Reincarnation vol 15", 13.8, "978-0-6368-0454-8", "https://kbimages1-a.akamaihd.net/cc08905b-6d4a-4ab2-8ea4-cc2ea879abcf/1200/1200/False/mushoku-tensei-jobless-reincarnation-light-novel-vol-15.jpg"));
        books.add(new Book("De meeste mensen deugen", 10.5, "978-6-6259-6059-2", "https://media.s-bol.com/NkD9WD1mzx48/798x1200.jpg"));

        users.add(new User("nameUser", 3));

        authors.add(new Author("Rudeus"));
        authors.add(new Author("Bindo"));
        authors.add(new Author("Audrey"));

        for (Book book : books) {

            for (Author author : authors) {
                author.getBookList().add(book);
                book.getAuthors().add(author);
            }

            for (User user : users) {
                user.getBookList().add(book);
                book.getUsersList().add(user);
            }

        }

        bookRepository.saveAll(books);
        userRepository.saveAll(users);
        authorRepository.saveAll(authors);


    }
}
