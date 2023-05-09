package com.bindothorpe.ewd_examenopdracht;

import domain.Author;
import domain.Book;
import domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String showBookOverview(@PathVariable("id") Long id, Model model, Authentication auth, RedirectAttributes redirectAttributes) {

        Book book = bookService.findById(id);

        if(book == null) {
            return "redirect:/books";
        }

        User user = userService.findByUsername(auth.getName());

        model.addAttribute("book", book);
        model.addAttribute("user", user);
        model.addAttribute("hasBook", book.getUsersList().contains(user));
        model.addAttribute("reachedBookLimit", user.getBookList().size() >= user.getMaxBooks());

        String message = (String) redirectAttributes.getFlashAttributes().get("message");
        if(message != null)
            model.addAttribute("message", message);

        return "bookOverview";
    }

    @PostMapping(value = "/add/{id}")
    public String addBookToUser(@PathVariable("id") Long id, Model model, Authentication auth, RedirectAttributes redirectAttributes) {
        bookService.addUserToUsersList(id, userService.findByUsername(auth.getName()).getId());
        redirectAttributes.addFlashAttribute("message", "Book added to your list");
        return "redirect:/books/" + id;
    }


    @PostMapping(value = "/remove/{id}")
    public String removeBookFromUser(@PathVariable("id") Long id, Model model, Authentication auth, RedirectAttributes redirectAttributes) {
        bookService.removeUserFromUsersList(id, userService.findByUsername(auth.getName()).getId());
        redirectAttributes.addFlashAttribute("message", "Book removed from your list");

        return "redirect:/books/" + id;
    }


}
