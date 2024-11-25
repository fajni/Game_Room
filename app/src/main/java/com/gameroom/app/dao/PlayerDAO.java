package com.gameroom.app.dao;

import com.gameroom.app.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PlayerDAO {

    private final EntityManager entityManager;

    @Autowired
    public PlayerDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public boolean savePlayer(Player player) {

        try {
            entityManager.persist(player);
        } catch (Exception e) {
            System.err.println("Could NOT SAVE Player " + player.toString());
            return false;
        }

        return true;
    }

    public Player findPlayerById(Long id) {

        Player player = null;

        try {
            player = entityManager.find(Player.class, id);
        } catch (Exception e) {
            System.err.println("Could NOT FIND Player with " + id + " id!");
        }

        return player;
    }

    public List<Player> findAllPlayers() {

        TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p", Player.class);
        List<Player> players = null;

        try {
            players = query.getResultList();
        } catch (Exception e) {
            System.err.println("Could NOT FIND Players!");
        }

        return players;
    }

    @Transactional
    public boolean deletePlayerById(Long id) {

        try {

            Player player = entityManager.find(Player.class, id);
            entityManager.remove(player);

        } catch (Exception e) {

            System.err.println("Could NOT DELETE Player with " + id + " id!");
            return false;
        }

        return true;
    }
}
