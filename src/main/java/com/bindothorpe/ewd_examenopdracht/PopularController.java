package com.bindothorpe.ewd_examenopdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.BookRepository;
import repository.UserRepository;

@Controller
@RequestMapping("/popular")
public class PopularController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showView(Model model, Authentication auth) {
        model.addAttribute("user", userRepository.findByUsername(auth.getName()));
        model.addAttribute("books", bookRepository.findMostSavedList());

        return "popular";
    }
}
