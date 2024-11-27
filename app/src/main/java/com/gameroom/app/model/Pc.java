package com.gameroom.app.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "pcs")
public class Pc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pc_number")
    private Long pcNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "active")
    private boolean active;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.REFRESH, CascadeType.DETACH,
                    CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE
            },
            mappedBy = "pc"
    )
    private Player player;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pc_detail_id")
    private PcDetail pcDetail;

    public Pc() {
    }

    public Pc(String title, boolean active) {
        this.title = title;
        this.active = active;
    }

    public Long getPcNumber() {
        return pcNumber;
    }

    public void setPcNumber(Long pcNumber) {
        this.pcNumber = pcNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PcDetail getPcDetail() {
        return pcDetail;
    }

    public void setPcDetail(PcDetail pcDetail) {
        this.pcDetail = pcDetail;
    }

    @Override
    public String toString() {
        return "Pc{" +
                "pcNumber=" + pcNumber +
                ", title='" + title + '\'' +
                ", active=" + active +
                '}';
    }
}
