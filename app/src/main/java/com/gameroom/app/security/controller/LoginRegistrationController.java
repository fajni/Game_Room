package com.gameroom.app.security.controller;

import com.gameroom.app.security.model.User;
import com.gameroom.app.security.service.RoleService;
import com.gameroom.app.security.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping("/users")
    @ResponseBody //json
    public List<User> showRegisteredUsers(Model model) {

        List<User> users = userService.findAllUsers();

        model.addAttribute("users", users);

        return users;
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
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {

        User u = userService.findUserByUsername(username);

        if (u == null) {
            System.out.println("User not found!");
            return "redirect:/showLoginPage";
        }

        if (!Objects.equals(u.getPassword(), "{noop}" + password)) {
            System.out.println("Invalid password!");
            return "redirect:/showLoginPage";
        }


        // authentication
        try {
            request.login(username, password); // https://www.baeldung.com/spring-security-auto-login-user-after-registration
        } catch (ServletException e) {
            System.err.println("Error while login! - " + e);
        }

        System.out.println("Login successful! - " + request.getRemoteUser());

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String myLogout(HttpServletRequest request) {

        System.out.println("My logout! - " + request.getRemoteUser());

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.invalidate(); // delete session that has been found
        }

        SecurityContextHolder.clearContext(); // delete authentication from Spring Security context

        // you can maybe use request.logout() instead all of the above...

        return "redirect:/";
    }
}
