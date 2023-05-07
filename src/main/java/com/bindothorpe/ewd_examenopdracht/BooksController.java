package com.bindothorpe.ewd_examenopdracht;

import domain.Book;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import repository.AuthorRepository;
import repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.UserRepository;
import service.BookService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showView(Model model, Authentication auth){
        model.addAttribute("user", userRepository.findByUsername(auth.getName()));
        model.addAttribute("books", bookService.findAll());

        return "books";
    }

    @GetMapping(value = "/{id}")
    public String showBookOverview(@PathVariable("id") Long id, Model model, Authentication auth) {

        Book book = bookService.findById(id);

        if(book == null) {
            return "redirect:/books";
        }

        model.addAttribute("book", book);
        model.addAttribute("user", userRepository.findByUsername(auth.getName()));
        model.addAttribute("hasBook", book.getUsersList().contains(userRepository.findByUsername(auth.getName())));

        return "bookOverview";
    }

    @PostMapping(value = "/add/{id}")
    public String addBookFromUser(@PathVariable("id") Long id, Model model, Authentication auth) {
        bookService.addUserToUsersList(id, userRepository.findByUsername(auth.getName()).getId());
        System.out.println("add called");
        return "redirect:/books/" + id;
    }

    @PostMapping(value = "/remove/{id}")
    public String removeBookFromUser(@PathVariable("id") Long id, Model model, Authentication auth) {
        bookService.removeUserFromUsersList(id, userRepository.findByUsername(auth.getName()).getId());
        System.out.println("remove called");
        return "redirect:/books/" + id;
    }


}
