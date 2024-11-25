package com.gameroom.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pc_details")
public class PcDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "gpu")
    private String gpu;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "ram")
    private String ram;

    @Column(name = "os")
    private String os;

    @OneToOne(mappedBy = "pcDetail")
    private Pc pc;

    public PcDetail() {

    }

    public PcDetail(String gpu, String cpu, String ram, String os, Pc pc) {
        this.gpu = gpu;
        this.cpu = cpu;
        this.ram = ram;
        this.os = os;
        this.pc = pc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Pc getPc() {
        return pc;
    }

    public void setPc(Pc pc) {
        this.pc = pc;
    }

    @Override
    public String toString() {
        return "PcDetail{" +
                "id=" + id +
                ", gpu='" + gpu + '\'' +
                ", cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
