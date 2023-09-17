package com.project.game.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE p.playerNumber=?1")
    Optional<Player> findPlayerByNumber(Long userNumber);

    @Query("SELECT p FROM Player p WHERE p.numberPC=?1")
    Optional<Player> findPlayerByPcNumber(Long numberPc);

    @Query("SELECT p FROM Player p WHERE p.name LIKE (%?1%)")
    List<Player> findPlayerByName(String name);

    //Optional ukoliko se vraca nulti ili jedan rezultat, (tacno 1 ili nijedan rezultat)
    //List aukoliko se vraca vise rezultata
}
