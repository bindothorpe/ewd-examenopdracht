package com.bindothorpe.ewd_examenopdracht.rest;

import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksRestController {

    @Autowired
    private BookService bookService;

    @GetMapping("/isbn/{isbn}")
    public Book getBookByISBN(@PathVariable("isbn") String isbn) {
        return bookService.findByISBN(isbn);
    }

    @GetMapping("/author/{authorName}")
    public List<Book> getBooksByAuthorName(@PathVariable("authorName") String authorName) {
        return bookService.findBooksByAuthorName(authorName);
    }

}
