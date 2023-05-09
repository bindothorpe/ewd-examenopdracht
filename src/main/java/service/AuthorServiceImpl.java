package service;

import domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void save(Author author) {
        if(authorRepository.findByName(author.getName()) == null) {
            authorRepository.save(author);
        }
    }

    @Override
    public void saveAll(Iterable<Author> authors) {
        authorRepository.saveAll(authors);
    }
}
