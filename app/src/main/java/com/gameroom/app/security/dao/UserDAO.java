package com.gameroom.app.security.dao;

import com.gameroom.app.security.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findUserByUsername(String username) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :data", User.class);
        query.setParameter("data", username);

        User user;

        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            System.err.println("User not found - " + username);
            user = null;
        }
        return user;
    }

    public User findUserByEmail(String email) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :data", User.class);
        query.setParameter("data", email);

        User user = null;

        try{
            user = query.getSingleResult();
        } catch (Exception e) {
            System.err.println("User email not found - " + email);
        }

        return user;
    }

    public void saveUser(User user) {

        entityManager.persist(user);
    }

    public List<User> findAllUsers() {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u ORDER BY u.username asc", User.class);
        return query.getResultList();
    }

}
