package com.gameroom.app.dao;

import com.gameroom.app.model.Pc;
import com.gameroom.app.model.PcDetail;
import com.gameroom.app.model.Player;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use the database connection from application.properties
@Import({PcDAO.class}) // you can use @SpringBootTest, but then you're loading up the whole app - meaning less performances.
public class PcDAOTest {

    private final PcDAO pcDAO;

    @Autowired
    public PcDAOTest(PcDAO pcDAO) {
        this.pcDAO = pcDAO;
    }


    @Test
    @Order(1)
    public void testFindPcById() {

        Pc pc = pcDAO.findPcById(1L);

        Pc testPc = new Pc("PC0", true);
        testPc.setPcNumber(1L);

        PcDetail testDetails = new PcDetail("Gtx 1050 Ti", "Ryzen 3 1200", "18 GB", "Windows 10", testPc);
        testDetails.setId(1);
        testPc.setPcDetail(testDetails);

        Player testPlayer = new Player("Veljko", "Fajnisevic", testPc);
        testPlayer.setPlayerId(1L);
        testPc.setPlayer(testPlayer);

        assertThat(pc.toString()).isEqualTo(testPc.toString());
        assertThat(pc.getPcDetail().toString()).isEqualTo(testPc.getPcDetail().toString());
        assertThat(pc.getPlayer().toString()).isEqualTo(testPc.getPlayer().toString());

    }

    @Test
    @Order(2)
    public void testSavePc() {

        Pc newPC = new Pc("PCTEST", false);

        pcDAO.savePc(newPC);

        for(Pc pc : pcDAO.findAllPcs()) {
            if(Objects.equals(pc.getTitle(), "PCTEST")) {

                assertThat(pc).isNotNull();

                System.out.println(pc.toString());

                return;
            }
        }


    }

    @Test
    @Order(3)
    public void testUpdatePc() {

        for(Pc pc : pcDAO.findAllPcs()) {
            if(Objects.equals(pc.getTitle(), "PCTEST")) {

                pc.setActive(true);

                pcDAO.updatePc(pc);

                Pc updatedPc = pcDAO.findPcById(pc.getPcNumber());

                assertThat(updatedPc.isActive()).isTrue();

                return;
            }
        }

    }

    @Test
    @Order(4)
    public void testDeletePc() {

        Long number = -1L;

        for(Pc pc : pcDAO.findAllPcs()) {
            if(Objects.equals(pc.getTitle(), "PCTEST")) {

                number = pc.getPcNumber();

                pcDAO.deletePcById(number);

                System.out.println(pc);

                return;
            }
        }

        assertThat(pcDAO.findPcById(number)).isNull();

    }

}
