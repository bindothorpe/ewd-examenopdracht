package repository;

import domain.Author;
import domain.User;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author findByName(String name);


}
