package repository;

import domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT u.maxBooks FROM User u WHERE u.id = :id")
    int findMaxBooksById(@Param("id")Long id);

}
