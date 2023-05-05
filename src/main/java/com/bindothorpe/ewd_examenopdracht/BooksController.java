package com.bindothorpe.ewd_examenopdracht;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String showView(Model model, Authentication auth){
        List<String> listRoles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        model.addAttribute("userListRoles", listRoles);

        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("book", bookRepository.findByISBN("978-6-6259-6059-2"));
        model.addAttribute("bookTitle", bookRepository.findByTitle("De meeste mensen deugen"));
        return "books";
    }
}
