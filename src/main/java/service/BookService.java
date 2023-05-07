package service;

import domain.Book;

import java.util.List;

public interface BookService {

    Iterable<Book> findAll();

    Book findById(Long id);

//    void removeUserFromUsersList(Long bookId, Long userId);
//
    void addUserToUsersList(Long bookId, Long userId);

    void removeUserFromUsersList(Long bookId, Long userId);
}
