package com.gameroom.app.security.dao;

import com.gameroom.app.security.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAO {

    private final EntityManager entityManager;

    @Autowired
    public RoleDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Role> findAllRoles() {

        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return query.getResultList();
    }

    public Role findRoleByRoleName(String roleName) {

        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName = :data", Role.class);
        query.setParameter("data", roleName);

        return query.getSingleResult();
    }

    public Role findRoleByRoleId(Long roleId) {

        return entityManager.find(Role.class, roleId);
    }
}
