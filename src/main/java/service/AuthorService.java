package service;

import domain.Author;

public interface AuthorService {

    void save(Author author);
    void saveAll(Iterable<Author> authors);
}
