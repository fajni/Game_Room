package com.gameroom.app.service;

import com.gameroom.app.dao.PlayerDAO;
import com.gameroom.app.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerDAO playerDAO;

    @Autowired
    public PlayerService(PlayerDAO playerDAO) {

        this.playerDAO = playerDAO;
    }

    @Transactional
    public boolean savePlayer(Player player) {

        try {
            playerDAO.savePlayer(player);
        } catch (Exception e) {
            System.err.println("Could NOT SAVE Player " + player.toString());
            return false;
        }

        return true;
    }

    public Player findPlayerById(Long id) {

        Player player = null;

        try{
            player = playerDAO.findPlayerById(id);
        } catch (Exception e) {
            System.err.println("Could NOT FIND Player with " + id + " id!");
        }

        return player;
    }

    public List<Player> findAllPlayers() {

        List<Player> players = null;

        try{
            players = playerDAO.findAllPlayers();
        } catch (Exception e) {
            System.err.println("Could NOT FIND Players!");
        }

        return players;

    }

    @Transactional
    public boolean deletePlayerById(Long id) {

        try{
            playerDAO.deletePlayerById(id);
        } catch (Exception e) {

            System.err.println("Could NOT DELETE Player with " + id + " id!");
            return false;
        }

        return true;
    }
}
