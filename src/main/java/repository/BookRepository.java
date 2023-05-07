package repository;

import domain.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findMostSavedList();
    Book findByISBN(String isbn);
    Book findByTitle(String title);

//    void removeUserFromUsersList(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_book_list (users_list_id, book_list_id) VALUES (:userId, :bookId)", nativeQuery = true)
    void addUserToUsersList(@Param("bookId") Long bookId, @Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_book_list l WHERE l.users_list_id = :userId AND book_list_id = :bookId)", nativeQuery = true)
    void removeUserFromUsersList(Long bookId, Long userId);
}
