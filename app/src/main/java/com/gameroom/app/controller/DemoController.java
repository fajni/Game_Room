package com.gameroom.app.controller;

import com.gameroom.app.security.model.User;
import com.gameroom.app.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DemoController {

    private final UserService userService;

    @Autowired
    public DemoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String landingPage() {
        return "landing-page";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/account")
    public String myAccount(Model model, HttpServletRequest request) {

        String username = request.getUserPrincipal().getName();
        User user = userService.findUserByUsername(username);

        System.out.println("User: " + user.toString());

        model.addAttribute("user", user);

        return "account";
    }

    @GetMapping("/accounts")
    public String accounts(Model model) {

        List<User> accounts = userService.findAllUsers();

        model.addAttribute("accounts", accounts);

        return "accounts";
    }

}
