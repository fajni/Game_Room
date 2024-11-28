package com.gameroom.app.security.service;

import com.gameroom.app.security.dao.RoleDAO;
import com.gameroom.app.security.dao.UserDAO;
import com.gameroom.app.security.model.Role;
import com.gameroom.app.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    private final RoleDAO roleDAO;

    @Autowired
    public UserService(UserDAO userDAO, RoleDAO roleDAO) {

        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Transactional
    public boolean saveUser(User user, String selectedRole) {

        Role role = new Role();
        role.setRoleName(selectedRole);
        role.setUser(user);

        user.setRole(role);
        user.setPassword("{noop}" + user.getPassword());

        try{
            userDAO.saveUser(user);
        } catch (Exception e) {
            System.err.println("Could NOT SAVE User - " + user);
            return false;
        }

        return true;
    }

    public User findUserByUsername(String username) {

        User user = null;

        try{
            user = userDAO.findUserByUsername(username);
        } catch (Exception e) {
            System.err.println("Could NOT FIND User with username: - " + username);
        }

        return user;
    }

    public List<User> findAllUsers() {

        return userDAO.findAllUsers();
    }
}
