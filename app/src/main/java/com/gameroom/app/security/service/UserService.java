package com.gameroom.app.security.service;

import com.gameroom.app.email.EmailService;
import com.gameroom.app.security.dao.RoleDAO;
import com.gameroom.app.security.dao.UserDAO;
import com.gameroom.app.security.jwt.JwtService;
import com.gameroom.app.security.model.Role;
import com.gameroom.app.security.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserDAO userDAO;

    private final RoleDAO roleDAO;

    private final EmailService emailService;

    private final JwtService jwtService;

    @Autowired
    public UserService(UserDAO userDAO, RoleDAO roleDAO, EmailService emailService, JwtService jwtService) {

        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.emailService = emailService;
        this.jwtService = jwtService;
    }

    @Transactional
    public boolean saveUser(User user, String selectedRole) {

        Role role = new Role();
        role.setRoleName(selectedRole);
        role.setUser(user);

        user.setRole(role);
        user.setPassword("{noop}" + user.getPassword());

        try {

            String text = "Dear " + user.getUsername().toUpperCase() + " you've successfully created account." + "\n" + "\n" +
                    "Username: " + user.getUsername() + "\n" + "Password: " + user.getPassword() + "\n" +
                    "Email: " + user.getEmail() + "\n" + "Role: " + user.getRole().getRoleName();

            if(emailService.sendMessage(user.getEmail(), "Registration successfully", text)){
                userDAO.saveUser(user);
            }

            return true;

        } catch (Exception e) {

            System.err.println("Error - " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteUser(Long userId) {

        return userDAO.deleteUser(userId);
    }

    public User findUserByUsername(String username) {

        User user = null;

        try {
            user = userDAO.findUserByUsername(username);
        } catch (Exception e) {
            System.err.println("Could NOT FIND User with username: - " + username);
        }

        return user;
    }

    public User findUserByEmail(String email) {

        User user = null;

        try {
            user = userDAO.findUserByEmail(email);
        } catch (Exception e) {
            System.err.println("Could NOT FIND User with email - " + email);
        }

        return user;
    }

    public List<User> findAllUsers() {

        return userDAO.findAllUsers();
    }

    public boolean verify(String email, String password, HttpServletRequest request) {

        User user = findUserByEmail(email);

        if (user == null) {
            System.out.println("User not found!");
            return false;
        }

        if (!Objects.equals(user.getPassword(), "{noop}" + password)) {
            System.out.println("Invalid password! - " + password);
            return false;
        }

        // authentication
        try {

            request.login(email, password); // https://www.baeldung.com/spring-security-auto-login-user-after-registration

        } catch (ServletException e) {
            System.err.println("Error while login! - " + e.getMessage());
            return false;
        }

        System.out.println("Login successful! - " + request.getRemoteUser());

        return true;
    }

    public void myLogout(HttpServletRequest request) {

        System.out.println("My logout! - " + request.getRemoteUser());

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.invalidate(); // delete session that has been found
        }

        SecurityContextHolder.clearContext(); // delete authentication from Spring Security context

    }

    public String generateToken(String email) {

        User user = findUserByEmail(email);
        String token = jwtService.generateToken(user);

        System.out.println("Token: " + token);
        System.out.println("Is Token Valid: " + jwtService.validateToken(token, user));

        return token;
    }
}
