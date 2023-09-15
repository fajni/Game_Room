package com.project.game.pc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PcConfig {
    //klasa za testiranje unosa podataka u tabelu

    //@Bean
    CommandLineRunner commandLineRunner(PcRepository pcRepository){
        return args -> {
            Pc PC1 = new Pc(
                    1L, "PC1", "ON"
            );

            Pc PC2 = new Pc(
                    2L, "PC2"
            );

            Pc PC3 = new Pc(
                    3L, "PC3"
            );
            pcRepository.saveAll(List.of(PC1, PC2, PC3));
        };
    }

}
