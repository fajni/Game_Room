package com.gameroom.app;

import com.gameroom.app.dao.PcDAO;
import com.gameroom.app.dao.PlayerDAO;
import com.gameroom.app.model.Pc;
import com.gameroom.app.model.PcDetail;
import com.gameroom.app.model.Player;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandLineRunnerApplication {

    public void savePcs(PcDAO pcDAO) {

        Pc pc1 = new Pc("PC1", false);

        Pc pc2 = new Pc("PC2", true);
        PcDetail pcDetail2 = new PcDetail("GTX 1050 Ti", "Ryzen 3 1200", "16GB", "Windows 10", pc2);
        pc2.setPcDetail(pcDetail2);

        Pc pc3 = new Pc("PC3", false);
        PcDetail pcDetail3 = new PcDetail("GTX 1060", "Ruzen 5 1600", "16GB", "Windows 11", pc3);

        pcDAO.savePc(pc1);
        pcDAO.savePc(pc2);
        pcDAO.savePcWithPcDetails(pc3, pcDetail3);

        System.out.println("PC1: " + pcDAO.findPcById(pc1.getPcNumber()).toString());
        System.out.println("===");

        System.out.println("PC2: " + pcDAO.findPcById(pc2.getPcNumber()).toString());
        System.out.println("PC2 Details: " + pc2.getPcDetail());
        System.out.println("===");

        System.out.println("All PCs: ");
        List<Pc> pcs = pcDAO.findAllPcs();
        for (Pc pc : pcs) {
            System.out.println(pc.toString());
        }
    }

    public void deletePc(PcDAO pcDAO) {

        System.out.println(pcDAO.deletePcById(1L));
        System.out.println(pcDAO.deletePcById(2L));
    }

    public void updatePc(PcDAO pcDAO) {

        Pc pc = pcDAO.findPcById(3L);
        pc.setActive(false);
        pc.setTitle("PC3");

        System.out.println(pcDAO.updatePc(pc));
    }

    public void savePlayer(PlayerDAO playerDAO, PcDAO pcDAO) {

        Pc pc = pcDAO.findPcById(3L);

        Player player = new Player("Veljko2", "Fajnisevic", pc);

        System.out.println(playerDAO.savePlayer(player));
    }

    public void deletePlayer(PlayerDAO playerDAO) {

        System.out.println(playerDAO.findPlayerById(1L));

        System.out.println(playerDAO.deletePlayerById(1L));
    }

    @Bean
    public CommandLineRunner commandLineRunner(PcDAO pcDAO, PlayerDAO playerDAO) {

        return runner -> {
        };
    }

}
