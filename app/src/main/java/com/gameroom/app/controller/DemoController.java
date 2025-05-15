package com.gameroom.app.controller;

import com.gameroom.app.security.model.User;
import com.gameroom.app.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        User user = userService.findUserByEmail(username);

        System.out.println("User: " + user.toString());

        model.addAttribute("user", user);

        return "account";
    }

    @PostMapping("/account/delete/{userId}")
    public String deleteAccount(@PathVariable Long userId, HttpServletRequest request) {

        System.out.println(userId);

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.invalidate(); // delete session that has been found
        }

        SecurityContextHolder.clearContext(); // delete authentication from Spring Security context

        userService.deleteUser(userId);

        return "redirect:/";
    }


    @GetMapping({"/accounts", "/users"})
    public String accounts(Model model) {

        List<User> accounts = userService.findAllUsers();

        model.addAttribute("accounts", accounts);

        return "accounts";
    }

}
