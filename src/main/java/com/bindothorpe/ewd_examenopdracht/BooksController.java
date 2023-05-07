package com.bindothorpe.ewd_examenopdracht;

import domain.Book;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BookService;
import service.UserService;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showView(Model model, Authentication auth){
        model.addAttribute("user", userService.findByUsername(auth.getName()));
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
        model.addAttribute("user", userService.findByUsername(auth.getName()));
        model.addAttribute("hasBook", book.getUsersList().contains(userService.findByUsername(auth.getName())));

        return "bookOverview";
    }

    @PostMapping(value = "/add/{id}")
    public String addBookToUser(@PathVariable("id") Long id, Model model, Authentication auth) {
        bookService.addUserToUsersList(id, userService.findByUsername(auth.getName()).getId());
        return "redirect:/books/" + id;
    }


    @PostMapping(value = "/remove/{id}")
    public String removeBookFromUser(@PathVariable("id") Long id, Model model, Authentication auth) {
        bookService.removeUserFromUsersList(id, userService.findByUsername(auth.getName()).getId());
        return "redirect:/books/" + id;
    }


}
