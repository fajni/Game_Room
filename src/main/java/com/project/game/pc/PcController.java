package com.project.game.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"api/game/pc", "api/game/pc/"})
public class PcController {

    @Autowired //klasa PcService se instancira od strane Spring Boot-a, posto objekti moraju biti instancirani
    private PcService pcService;

//    @Autowired
//    public PcController(PcService pcService){
//        this.pcService=pcService;
//    }

    @GetMapping
    public ModelAndView getPcs() {
        ModelAndView model = new ModelAndView("pc/pcs"); //html name
        model.addObject("pcs", pcService.getPcs()); //objekti se prosledjuju html ???
        return model;
    }

    @GetMapping("/json")
    //GetMapping koristi se za dobijanje resursa iz sistema. Dobija request zahtev od korisnika i vraca odgovor/response.
    public List<Pc> getPcsJson() {
        return pcService.getPcs();
    }

    @GetMapping("/{pcNumber}") //za dobijanje 1 pc preko linka
    public ResponseEntity<Optional<Pc>> getSinglePc(@PathVariable Long pcNumber) {
        return new ResponseEntity<Optional<Pc>>(pcService.getPc(pcNumber), HttpStatus.OK);
    }

    @GetMapping("/search") //Search Bar, name se povezuje sa input u html ???
    public Pc getPc(@RequestParam(name="pcNumber") Long pcNumber){
        return pcService.getPc(pcNumber).get();
    }

    @GetMapping("/removePc") //brisanje /removePc?pcNumber=1
    public ModelAndView removePc(@RequestParam Long pcNumber) {
        pcService.deletePc(pcNumber);
        return new ModelAndView("redirect:/api/game/pc");
    }

    @GetMapping("/create_pc") //za formu dodavanje novog pc-a, povezana je zajedno sa PostMapping("/savePC")
    public ModelAndView submitPc() {
        ModelAndView model = new ModelAndView("pc/create_pc");
        Pc newPc = new Pc(); //prazan objekat u koji ce se sacuvati uneti podaci iz forme
        model.addObject("pc", newPc);
        return model;
    }

    @PostMapping("/savePc") //nakon pozvane forme create_pc, potrebno je sacuvati objekat
    public ModelAndView savePc(@ModelAttribute Pc pc) {
        pcService.addNewPc(pc);
        return new ModelAndView("redirect:/api/game/pc");
    }

    @GetMapping("/update_pc")
    public ModelAndView updatePcForm(@RequestParam Long pcNumber) {
        ModelAndView model = new ModelAndView("pc/update_pc");
        Pc updatePc = pcService.getPc(pcNumber).get();
        model.addObject("pc", updatePc);
        return model;
    }

    @PostMapping("/updatePc")
    public ModelAndView pcUpdate(
            @RequestParam("pcNumber") Long pcNumber,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String title
    ) {
        pcService.updatePc(pcNumber, title, status);
        return new ModelAndView("redirect:/api/game/pc");
    }

    @PostMapping //koristi se ukoliko zelis da dodas novi resurs u svoji sistem
    public void addNewPc(@RequestBody Pc pc) {
        pcService.addNewPc(pc);
    }

    @DeleteMapping(path = {"/delete/{pcNumber}"}) //path = "{pcNumber}", ako je ovako onda ide bez /delete
    public void deletePc(@PathVariable("pcNumber") Long pcNumber) {
        pcService.deletePc(pcNumber);
    }

    @PutMapping(path = "/{pcNumber}") //azuriranje
    public void updatePc(
            @PathVariable("pcNumber") Long pcNumber,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String title //required = false, ne moraju da se prosledjuju metodi kao parametri!
    ) {
        pcService.updatePc(pcNumber, title, status);
    }
}
