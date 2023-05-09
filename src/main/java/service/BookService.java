package service;

import domain.Book;

public interface BookService {

    Iterable<Book> findAll();
    Book findById(Long id);
    Book findByISBN(String isbn);
    void addUserToUsersList(Long bookId, Long userId);
    void removeUserFromUsersList(Long bookId, Long userId);

}
