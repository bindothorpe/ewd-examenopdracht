package service;

import domain.Book;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookService {

    Iterable<Book> findAll();
    Book findById(Long id);
    Book findByISBN(String isbn);
    void addUserToUsersList(Long bookId, Long userId);
    void removeUserFromUsersList(Long bookId, Long userId);
    void save(Book book);

    List<Book> findBooksByAuthorId(Long authorId);
    List<Book> findBooksByAuthorName( String authorName);



}
