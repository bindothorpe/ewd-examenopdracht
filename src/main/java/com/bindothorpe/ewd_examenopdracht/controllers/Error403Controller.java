package com.bindothorpe.ewd_examenopdracht.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;
import util.Text;

@Controller
public class Error403Controller implements ErrorController {

    @Autowired
    private UserService userService;

    @GetMapping("/error")
    public String showView(HttpServletRequest request, Model model, Authentication auth) {

        model.addAttribute("user", userService.findByUsername(auth.getName()));
        model.addAttribute("role", Text.refactorRoleName(auth.getAuthorities().toArray()[0].toString()));


        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            return switch (statusCode) {
                case 403 -> "403";
                case 500-> "500";
                default -> "404";
            };


        }
        return "404";
    }

}
