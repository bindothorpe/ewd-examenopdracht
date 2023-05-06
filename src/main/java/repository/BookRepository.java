package repository;

import domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findMostSavedList();
    Book findByISBN(String isbn);
    Book findByTitle(String title);

}
