package service;

import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book findByISBN(String isbn) {
        return bookRepository.findByISBN(isbn);
    }

    @Override
    public void addUserToUsersList(Long bookId, Long userId) {
        bookRepository.addUserToUsersList(bookId, userId);
    }

    @Override
    public void removeUserFromUsersList(Long bookId, Long userId) {
        bookRepository.removeUserFromUsersList(bookId, userId);
    }
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> findBooksByAuthorId(Long authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }

    @Override
    public List<Book> findBooksByAuthorName(String authorName) {
        return bookRepository.findBooksByAuthorName(authorName);
    }


}
