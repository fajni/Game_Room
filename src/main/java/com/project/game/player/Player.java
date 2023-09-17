package com.project.game.player;

import com.project.game.pc.Pc;
import jakarta.persistence.*;

@Entity
@Table(name = "players") //ne moze da se koristi user jer je ta rec rezervisana u postgresql
public class Player {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerNumber;
    private String name;
    private String lastname;

    private Long numberPC;

    @OneToOne
    @JoinColumn(name = "pc_number", referencedColumnName = "pcNumber")
    private Pc pc;

    public Player() {
    }

    public Player(Long playerNumber, String name, String lastname, Long numberPc) {
        super();
        this.playerNumber = playerNumber;
        this.name = name;
        this.lastname = lastname;
        this.numberPC = numberPc;
    }

    public Long getPlayerNumber() {
        return playerNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setPlayerNumber(Long playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setNumberPC(Long numberPc) {
        this.numberPC = numberPc;
    }

    public Long getNumberPC() {
        return numberPC;
    }
}
