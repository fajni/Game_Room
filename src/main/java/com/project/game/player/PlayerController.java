package com.project.game.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"api/game/player", "api/game/player/"})
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ModelAndView getPlayers() {
        ModelAndView model = new ModelAndView("player/players");
        model.addObject("players", playerService.getPlayers());
        return model;

        //return new ModelAndView().addObject("players", playerService.getPlayers());
    }

    @GetMapping("/json")
    public List<Player> getPlayersJson() {
        return playerService.getPlayers();
    }

    @GetMapping("/{playerNumber}")
    public ResponseEntity<Optional<Player>> getSinglePlayer(@PathVariable Long playerNumber) {
        return new ResponseEntity<Optional<Player>>(playerService.findSinglePlayerByPcNumber(playerNumber), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Player> getPlayer(@RequestParam(name = "name") String name) {
        List<Player> players = playerService.getPlayerByName(name);
        return players;
    }

    @GetMapping("/pc/{numberPc}") //dobijanje playera preko pcNumber
    public ResponseEntity<Optional<Player>> getSinglePlayerByPcNumber(@PathVariable Long numberPc) {
        return new ResponseEntity<Optional<Player>>(playerService.findSinglePlayerByPcNumber(numberPc), HttpStatus.OK);
    }

    @GetMapping("/removePlayer") //removePlayer?={playerNumber}
    public ModelAndView removePlayer(@RequestParam Long playerNumber) {
        playerService.deletePlayer(playerNumber);
        return new ModelAndView("redirect:/api/game/player");
    }

    @GetMapping("/create_player")
    public ModelAndView submitPlayer() {
        ModelAndView model = new ModelAndView("player/create_player");//html file
        Player newPlayer = new Player();
        model.addObject("player", newPlayer);
        return model;
    }

    @PostMapping("/savePlayer")
    public ModelAndView savePlayer(@ModelAttribute Player player) {
        playerService.addNewPlayer(player);
        return new ModelAndView("redirect:/api/game/player");
    }

    @GetMapping("/update_player")
    public ModelAndView updatePlayer(@RequestParam Long playerNumber) {
        ModelAndView model = new ModelAndView("player/update_player");
        Player updatePlayer = playerService.getPlayer(playerNumber);
        model.addObject("player", updatePlayer);
        return model;
    }

    @PostMapping("/updatePlayer")
    public ModelAndView playerUpdate(
            @RequestParam("playerNumber") Long playerNumber,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) Long numberPC
    ) {
        playerService.updatePlayer(playerNumber, name, lastname, numberPC);
        return new ModelAndView("redirect:/api/game/player");
    }

    @PostMapping //proveriti!!! addNewPlayer??
    public void addNewPlayer(@RequestBody Player player) {
        playerService.addNewPlayer(player);
    }

    @DeleteMapping(path = "/delete/{playerNumber}")
    public void deletePlayer(@PathVariable("playerNumber") Long playerNumber) {
        playerService.deletePlayer(playerNumber);
    }

    @PutMapping(path = "{playerNumber}")
    public void updatePlayer(@PathVariable("playerNumber") Long playerNumber,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String lastname,
                           @RequestParam(required = false) Long pcNumber) {
        playerService.updatePlayer(playerNumber, name, lastname, pcNumber);
    }
}
