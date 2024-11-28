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

    public void savePlayer(Player player) {

        entityManager.persist(player);
    }

    public Player findPlayerById(Long id) {

        return entityManager.find(Player.class, id);
    }

    public List<Player> findAllPlayers() {

        TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p", Player.class);
        return query.getResultList();
    }

    public void deletePlayerById(Long id) {

        Player player = entityManager.find(Player.class, id);
        entityManager.remove(player);
    }

    public void updatePlayer(Player player) {

        entityManager.merge(player);
    }
}
