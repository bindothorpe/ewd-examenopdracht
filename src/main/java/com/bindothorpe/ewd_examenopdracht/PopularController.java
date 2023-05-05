package com.bindothorpe.ewd_examenopdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.BookRepository;

@Controller
@RequestMapping("/popular")
public class PopularController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String showView(Model model) {
        return "popular";
    }
}
