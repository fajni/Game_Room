package com.project.game.user;

import com.project.game.pc.Pc;
import jakarta.persistence.*;

@Entity
@Table(name = "users") //ne moze da se koristi user jer je ta rec rezervisana u postgresql
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long userNumber;
    private String name;
    private String lastname;

    private Long numberPC;

    @OneToOne
    @JoinColumn(name = "pc_number", referencedColumnName = "pcNumber")
    private Pc pc;

    public User() {
    }

    public User(Long userNumber, String name, String lastname, Long numberPc) {
        super();
        this.userNumber = userNumber;
        this.name = name;
        this.lastname = lastname;
        this.numberPC = numberPc;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
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
