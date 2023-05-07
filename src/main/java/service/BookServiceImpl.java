package service;

import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

//    @Override
//    public void removeUserFromUsersList(Long bookId, Long userId) {
//        bookRepository.findById(bookId).ifPresent(book -> {
//            book.getUsersList().removeIf(user -> user.getId().equals(userId));
//            bookRepository.save(book);
//        });
//    }
//
    @Override
    public void addUserToUsersList(Long bookId, Long userId) {
        bookRepository.addUserToUsersList(bookId, userId);
    }

    @Override
    public void removeUserFromUsersList(Long bookId, Long userId) {
        bookRepository.removeUserFromUsersList(bookId, userId);
    }
}
