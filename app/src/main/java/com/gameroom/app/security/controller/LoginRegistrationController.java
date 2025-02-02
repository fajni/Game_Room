package com.gameroom.app.security.controller;

import com.gameroom.app.security.model.User;
import com.gameroom.app.security.service.RoleService;
import com.gameroom.app.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginRegistrationController {

    @Value("${roles}")
    private List<String> roleNames;

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public LoginRegistrationController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage() {

        return "login-page";
    }

    @GetMapping("/showRegisterPage")
    public String showRegisterPage(Model model) {

        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("roles", roleNames);

        return "registration-page";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {

        return "access-denied";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("user") User user) {

        User exist = userService.findUserByUsername(user.getUsername());

        if (exist != null) {
            throw new RuntimeException("User already exist!");
        }

        System.out.println(userService.saveUser(user, user.getRole().getRoleName()));

        return "redirect:/showLoginPage";
    }

    @PostMapping("/myLogin")
    public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {

        if(!userService.verify(email, password, request)){
            return "redirect:/showLoginPage";
        }

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String myLogout(HttpServletRequest request) {

        userService.myLogout(request);

        return "redirect:/";
    }
}
