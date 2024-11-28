package com.gameroom.app.security.service;

import com.gameroom.app.security.dao.RoleDAO;
import com.gameroom.app.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Lazy
public class RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {

        this.roleDAO = roleDAO;
    }

    public List<Role> findAllRoles() {

        List<Role> roles = new ArrayList<>();

        try{
            roles = roleDAO.findAllRoles();
        } catch (Exception e) {
            System.err.println("Could NOT FIND Roles!");
        }

        return roles;
    }

    public Role findRoleByRoleName(String roleName) {

        Role role = new Role();

        try{
            role = roleDAO.findRoleByRoleName(roleName);
        } catch (Exception e) {
            System.err.println("Could NOT FIND Role - " + roleName);
        }

        return role;
    }

}
