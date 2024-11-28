package com.gameroom.app.controller;

import com.gameroom.app.model.Pc;
import com.gameroom.app.model.PcDetail;
import com.gameroom.app.model.Player;
import com.gameroom.app.service.PcService;
import com.gameroom.app.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    private final PcService pcService;

    @Autowired
    public PlayerController(PlayerService playerService, PcService pcService) {
        this.playerService = playerService;
        this.pcService = pcService;
    }

    /* SHOW - GET MAPPINGS */
    @GetMapping("/players")
    public String showAllPlayers(Model model) {

        List<Player> players = playerService.findAllPlayers();

        model.addAttribute("players", players);

        return "player/players";
    }

    @GetMapping("/players/{playerId}")
    public String showPLayer(Model model, @PathVariable("playerId") Long playerId) {

        Player player = playerService.findPlayerById(playerId);

        model.addAttribute("player", player);

        return "player/show-player";
    }


    /* DELETE MAPPINGS */
    @GetMapping("/remove/player/{playerId}")
    public String deletePlayerGet(@PathVariable("playerId") Long playerId) {

        Player p = playerService.findPlayerById(playerId);
        System.out.println(p);

        playerService.deletePlayerById(playerId);

        Pc pc = p.getPc();
        pc.setActive(false);
        pcService.updatePc(pc);
        System.out.println(pc);

        return "redirect:/players";
    }

    @DeleteMapping("/remove/player/{playerId}")
    public void deletePlayer(@PathVariable("playerId") Long playerId) {

        playerService.deletePlayerById(playerId);
    }


    /* ADD - POST MAPPING */
    @GetMapping("/save/player")
    public String showAddPlayerPage(Model model) {

        List<Long> pcs = new ArrayList<>();
        for (Pc pc : pcService.findAllPcs())
            if (pc.getPlayer() == null)
                pcs.add(pc.getPcNumber());

        model.addAttribute("pcs", pcs);
        model.addAttribute("player", new Player());

        return "/player/add-player";
    }

    @PostMapping("/save/player")
    public String addNewPlayer(@ModelAttribute("player") Player player) {

        System.out.println("Added New Player: " + player.toString());

        Pc pc = pcService.findPcById(player.getPc().getPcNumber());
        pc.setActive(true);

        playerService.savePlayer(player);

        return "redirect:/players";
    }


    /* UPDATE - PUT MAPPING */
    @GetMapping("/update/player/{playerId}")
    public String showPlayerUpdate(Model model, @PathVariable("playerId") Long playerId) {

        Player player = playerService.findPlayerById(playerId);

        model.addAttribute("player", player);

        return "/player/update-player";
    }

    @PutMapping("/update/player/{playerId}")
    public String updatePlayer(@PathVariable("playerId") Long playerId) {

        Player player = playerService.findPlayerById(playerId);
        System.out.println(player.toString());

        return "redirect:/players";
    }

    @PostMapping("/form/update/player/{playerId}")
    public String updatePlayerGet(@ModelAttribute("player") Player player) {


        Pc pc = pcService.findPcById(player.getPc().getPcNumber());
        PcDetail pcDetail = pc.getPcDetail();

        System.out.println(playerService.updatePlayer(player));
        pc.setPcDetail(pcDetail);
        System.out.println(pcService.updatePc(pc));

        return "redirect:/players";
    }
}