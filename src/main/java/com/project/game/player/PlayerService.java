package com.project.game.player;

import com.project.game.pc.Pc;
import com.project.game.pc.PcRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PcRepository pcRepository;

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayer(Long playerNumber) {
        return playerRepository.findPlayerByNumber(playerNumber).get();
    }

    public List<Player> getPlayerByName(String name){

        String firstLetter = name.substring(0, 1).toUpperCase();
        String remainingLetters = name.substring(1, name.length());
        name=firstLetter+remainingLetters; //ukoliko se prosledi ime sa malim slovima

        return playerRepository.findPlayerByName(name);
    }

    public Optional<Player> findSinglePlayerByPlayerNumber(Long playerNumber) {
        return playerRepository.findPlayerByNumber(playerNumber);
    }

    public Optional<Player> findSinglePlayerByPcNumber(Long numberPc) {
        return playerRepository.findPlayerByPcNumber(numberPc);
    }

    public void addNewPlayer(Player player) {
        Optional<Player> playerOptional = playerRepository.findById(player.getPlayerNumber());
        List<Player> playerAll = playerRepository.findAll();

        Optional<Pc> pcFind = pcRepository.findPcByNumber(player.getNumberPC());

        if (playerOptional.isPresent())
            throw new IllegalStateException("Player number " + player.getPlayerNumber() + " is already taken!");

        if (player.getNumberPC() == null)
            throw new IllegalStateException("Player needs a pc number!");

        List<Player> players = getPlayers();
        for (int i = 0; i < players.size(); i++)
            if (Objects.equals(players.get(i).getName(), player.getName()) && Objects.equals(players.get(i).getLastname(), player.getLastname()))
                throw new IllegalStateException("Player " + player.getName() + " " + player.getLastname() + " already exist!");

        for (int i = 0; i < playerAll.size(); i++) {
            if (player.getNumberPC() == playerAll.get(i).getNumberPC())
                throw new IllegalStateException("Pc with number " + player.getNumberPC() + " is already taken!");
        }

        //provera da li postoji racunar sa takvim number! Mozda da sam napravis bazu!
        if (pcFind.isEmpty())
            throw new IllegalStateException("Pc with number " + player.getNumberPC() + " does not exist!");

        //dodavanje novog racunara (azuriranje) pc statusa na ON ukoliko je player.playerNumber = pc.playerNumber
        pcFind.get().setStatus("ON");
        pcFind.get().setPlayerNumber(player.getPlayerNumber());

        playerRepository.save(player);
        System.out.println("Added player: " + player.getName() + " " + player.getLastname());
    }

    public void deletePlayer(Long playerNumber) {
        Optional<Player> playerOptional = playerRepository.findById(playerNumber);
        Optional<Pc> pcFind = pcRepository.findPcByNumber(playerOptional.get().getNumberPC());

        if (playerNumber == null)
            throw new IllegalStateException("Value can't be null!");

        if (!playerRepository.existsById(playerNumber))
            throw new IllegalStateException("Player with number " + playerNumber + " does not exist!");

        //ukoliko se obrise player polja u pc se postavljaju na OFF i null
        pcFind.get().setStatus("OFF");
        pcFind.get().setPlayerNumber(null);

        playerRepository.deleteById(playerNumber);
        System.out.println("Removed player: " + playerOptional.get().getName() + " " + playerOptional.get().getLastname());
    }

    @Transactional
    public void updatePlayer(Long playerNumber, String name, String lastname, Long numberPC) {
        Player player = playerRepository.findPlayerByNumber(playerNumber)
                .orElseThrow(() -> new IllegalStateException("Player with number " + playerNumber + " doesn't exist!"));
        Pc currentPc = pcRepository.findPcByNumber(player.getNumberPC()).get(); //trenutno
        //if(!Objects.equals(numberPC, null))
        Pc pc = pcRepository.findPcByNumber(numberPC)
                .orElseThrow(() -> new IllegalStateException("Pc with number " + numberPC + " does not exist!")); //prosledjeni pc

        List<Player> players = playerRepository.findAll();

        //ukoliko korisnik postoji sa istim name i lastname, nije dobro
        for(int i = 0; i< players.size(); i++){
            if(Objects.equals(players.get(i).getName(), name) &&
                    Objects.equals(players.get(i).getLastname(), lastname) &&
                    !Objects.equals(players.get(i).getName(), player.getName()) &&
                    !Objects.equals(players.get(i).getLastname(), lastname))
                throw new IllegalStateException("Player "+name+" "+lastname+" already exist!");
        }

        //ukoliko se prosledi prazna imena
        if (name.isEmpty() || name.isBlank())
            throw new IllegalStateException("Player must have a name!");
        if (lastname.isEmpty() || lastname.isBlank())
            throw new IllegalStateException("Player must have a lastname!");
        if (numberPC.toString().isBlank() || numberPC.toString().isEmpty())
            throw new IllegalStateException("Player must have a PC number!");

        if(pc.getPcNumber() == currentPc.getPcNumber())
            player.setNumberPC(numberPC);

        //ukoliko pc nije null znaci da ima korisnika
        if(!Objects.equals(pc.getPlayerNumber(),null) && pc.getPcNumber() != currentPc.getPcNumber())
            throw new IllegalStateException("Pc "+pc.getPcNumber()+" already has a player!");
        player.setNumberPC(numberPC);

        if(!Objects.equals(name, null) && (!name.isBlank() || !name.isEmpty()))
            player.setName(name);
        if(!Objects.equals(lastname, null) && (!lastname.isBlank() || !lastname.isEmpty()))
            player.setLastname(lastname);

        currentPc.setPlayerNumber(null);
        currentPc.setStatus("OFF");

        pc.setPlayerNumber(playerNumber);
        pc.setStatus("ON");
    }
}
