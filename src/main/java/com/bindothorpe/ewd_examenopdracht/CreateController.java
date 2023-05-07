package com.bindothorpe.ewd_examenopdracht;

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
import repository.BookRepository;
import service.UserService;
import validator.BookRegistrationValidator;

@Controller
@RequestMapping("/create")
public class CreateController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookRegistrationValidator validator;

    @GetMapping
    public String showView(Model model, Authentication auth) {
        model.addAttribute("user", userService.findByUsername(auth.getName()));
//        model.addAttribute("error", false);
        model.addAttribute("bookRegistration", new BookRegistration());
        return "create";
    }

    @PostMapping
    public String processBookRegistration(@Valid BookRegistration bookRegistration, BindingResult result, Model model, Authentication auth) {
        validator.validate(bookRegistration, result);

        model.addAttribute("user", userService.findByUsername(auth.getName()));


        if(result.hasErrors()) {
            model.addAttribute("error", true);
            return "create";
        }

        return "create";
    }

}
