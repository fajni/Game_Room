package com.project.game.pc;

import com.project.game.player.Player;
import jakarta.persistence.*;

@Entity
@Table(name = "pcs")
public class Pc {

    @Id
//    @SequenceGenerator(
//            name = "pc_sequence",
//            sequenceName = "pc_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.AUTO,
//            generator = "pc_sequence"
//    )

    private Long pcNumber;
    private String title;
    private String status;
    private Long playerNumber;

    @OneToOne(mappedBy = "pc")
    private Player player;

    public Pc() {
    }

    public Pc(Long pcNumber, String title, String status, Long playerNumber) {
        super();
        this.pcNumber = pcNumber;
        this.title = title;
        this.status = status;
        this.playerNumber = playerNumber;
    }

    public Pc(String title) {
        super();
        this.title = title;
    }

    public Pc(Long pcNumber, String title) {
        super();
        this.pcNumber = pcNumber;
        this.title = title;
    }

    public Pc(Long pcNumber, String title, String status) {
        this.pcNumber = pcNumber;
        this.title = title;
        this.status = status;
    }

    public void setPcNumber(Long pcNumber) {
        this.pcNumber = pcNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPcNumber() {
        return pcNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public void setPlayerNumber(Long playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Long getPlayerNumber() {
        return playerNumber;
    }
}
