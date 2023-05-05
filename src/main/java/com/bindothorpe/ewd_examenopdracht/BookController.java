package com.bindothorpe.ewd_examenopdracht;

import repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String showView(Model model) {
//        model.addAttribute("mostSaved", bookRepository.findMostSaved());
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("book", bookRepository.findByISBN("978-6-6259-6059-2"));
        model.addAttribute("bookTitle", bookRepository.findByTitle("De meeste mensen deugen"));
        return "book";
    }
}
