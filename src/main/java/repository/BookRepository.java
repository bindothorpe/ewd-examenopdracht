package repository;

import domain.Book;
import org.springframework.data.repository.CrudRepository;
public interface BookRepository extends CrudRepository<Book, Long> {

//    List<Book> findMostSaved();

    Book findByISBN(String isbn);
    Book findByTitle(String title);
}
