package com.bindothorpe.ewd_examenopdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;
import util.Text;

@Controller
@RequestMapping("/403")
public class Error403Controller {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showView(Model model, Authentication auth) {

        model.addAttribute("user", userService.findByUsername(auth.getName()));
        model.addAttribute("role", Text.refactorRoleName(auth.getAuthorities().toArray()[0].toString()));

        return "403";
    }

}
