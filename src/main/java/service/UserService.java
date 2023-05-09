package service;

import domain.User;

public interface UserService {

    User findByUsername(String username);

    int findMaxBooksById(Long id);

}
