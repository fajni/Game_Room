package com.project.game.pc;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service //@Component
public class PcService{

    @Autowired
    private PcRepository pcRepository;

    public List<Pc> getPcs() {
        return pcRepository.findAll();
    }

    public Optional<Pc> getPc(Long pcNumber) {
        return pcRepository.findPcByNumber(pcNumber);
    }

    public void addNewPc(Pc pc) {
        Optional<Pc> pcOptionalNumber = pcRepository.findById(pc.getPcNumber());
        Optional<Pc> pcOptionalTitle = pcRepository.findPcByPcTitle(pc.getTitle());

        if (pcOptionalNumber.isPresent()) {
            throw new IllegalStateException("Number " + pc.getPcNumber() + " is taken!");
        }
        if (pcOptionalTitle.isPresent())
            throw new IllegalStateException("Title " + pc.getTitle() + " for pc number " + pc.getPcNumber() + " is already taken!");

        if (pc.getStatus() == null || pc.getStatus().isBlank() || pc.getStatus().isEmpty())
            pc.setStatus("OFF");
        if (!(pc.getStatus().equals("ON") || pc.getStatus().equals("OFF")))
            throw new IllegalStateException("PC status can only be ON or OFF!");
        if (pc.getTitle() == null || pc.getTitle().isBlank()) //postavljanje title za pc ukoliko nije prosledjen kroz request
            pc.setTitle("PC" + pc.getPcNumber());

        pcRepository.save(pc);
        System.out.println("Added: " + pc.getTitle());
    }

    public void deletePc(Long pcNumber) {

        Optional<Pc> pc = pcRepository.findPcByNumber(pcNumber);

        if (pcNumber.equals(null))
            throw new IllegalStateException("Value can't be null!");

        boolean exists = pcRepository.existsById(pcNumber);
        if (!exists)
            throw new IllegalStateException("Pc with number " + pcNumber + " does not exist!");

        if (!Objects.equals(pc.get().getUserNumber(), null))
            throw new IllegalStateException("Pc has a user!");

        pcRepository.deleteById(pcNumber);
    }

    @Transactional
    public void updatePc(Long pcNumber, String title, String status) {

        Pc pc = pcRepository.findById(pcNumber)
                .orElseThrow(() -> new IllegalStateException("Pc with number " + pcNumber + " does not exist!"));

        if (pc.getUserNumber() != null)
            throw new IllegalStateException("Pc is currently in use!");

        if (title != null && title.length() > 0 && !Objects.equals(pc.getTitle(), title)) {
            Optional<Pc> pcOptional = pcRepository.findPcByPcTitle(title);
            if (pcOptional.isPresent()) //ukoliko je vec title zauzeto
                throw new IllegalStateException("Title (\"" + title + "\") is already taken!");
            pc.setTitle(title);
        }

        if (!(Objects.equals(status, "ON") || Objects.equals(status, "OFF") || Objects.equals(status, null))) //ne moze .equals() jer se prosledjuje i null ukoliko se menja samo title
            throw new IllegalStateException("Status for pc can only be ON or OFF.");

        if (status != null && status.length() > 0 && !Objects.equals(pc.getStatus(), status))
            pc.setStatus(status);
    }

    //add multiple pc
}
