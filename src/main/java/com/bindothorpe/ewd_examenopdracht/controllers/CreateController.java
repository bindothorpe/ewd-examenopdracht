package com.bindothorpe.ewd_examenopdracht.controllers;

import domain.Author;
import domain.Book;
import domain.Location;
import form.BookRegistration;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.AuthorService;
import service.BookService;
import service.LocationService;
import service.UserService;
import util.Text;
import validator.BookRegistrationValidator;

import java.util.List;

@Controller
@RequestMapping("/create")
public class CreateController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookRegistrationValidator validator;

    @GetMapping
    public String showView(Model model, Authentication auth) {
        model.addAttribute("user", userService.findByUsername(auth.getName()));
        model.addAttribute("role", Text.refactorRoleName(auth.getAuthorities().toArray()[0].toString()));
        model.addAttribute("bookRegistration", new BookRegistration());
        return "create";
    }

    @PostMapping
    public String processBookRegistration(@Valid BookRegistration bookRegistration, BindingResult result, Model model, Authentication auth) {
        validator.validate(bookRegistration, result);

        model.addAttribute("user", userService.findByUsername(auth.getName()));
        model.addAttribute("role", Text.refactorRoleName(auth.getAuthorities().toArray()[0].toString()));


        if(result.hasErrors()) {
            model.addAttribute("error", true);
            return "create";
        }

        Book book = bookRegistration.getBook();
        List<Location> locations = bookRegistration.getLocations();
        List<Author> authors = bookRegistration.getAuthors();

        for(Location loc : locations) {
            loc.setBook(book);
            book.getLocations().add(loc);
        }

        for(Author author : authors) {
            author.getBookList().add(book);
            book.getAuthors().add(author);
        }

        bookService.save(book);
        authorService.saveAll(authors);
        locationService.saveAll(locations);

        Book b = bookService.findByISBN(book.getISBN());

        return "redirect:books/" + b.getId();
    }

}
