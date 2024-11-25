package com.gameroom.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.REFRESH, CascadeType.DETACH,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "pc_number")
    private Pc pc;

    public Player() {

    }

    public Player(String firstName, String lastName, Pc pc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pc = pc;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Pc getPc() {
        return pc;
    }

    public void setPc(Pc pc) {
        this.pc = pc;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
