package com.gameroom.app.controller;

import com.gameroom.app.model.Pc;
import com.gameroom.app.model.PcDetail;
import com.gameroom.app.service.PcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PcController {

    private final PcService pcService;


    @Autowired
    public PcController(PcService pcService) {

        this.pcService = pcService;
    }


    /* SHOW - GET MAPPINGS */
    @GetMapping({"/pcs/all", "/pcs/"})
    public String showAllPcs(Model model) {

        List<Pc> pcs = pcService.findAllPcs();

        model.addAttribute("pcs", pcs);

        return "/pc/pcs";
    }

    @GetMapping("/pcs/{pcNumber}")
    public String showPc(Model model, @PathVariable("pcNumber") Long pcNumber) {

        Pc pc = pcService.findPcById(pcNumber);

        model.addAttribute("pc", pc);

        return "/pc/show-pc";
    }


    /* DELETE MAPPINGS */
    @GetMapping("/remove/pc/{pcNumber}") // get mapping for delete - html can't send delete request
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // testing @PreAuthorize annotation
    public String deletePcGet(@PathVariable("pcNumber") Long pcNumber) {

        pcService.deletePcById(pcNumber);

        return "redirect:/pcs";
    }

    @DeleteMapping("/remove/pc/{pcNumber}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deletePc(@PathVariable("pcNumber") Long pcNumber) {

        pcService.deletePcById(pcNumber);
    }


    /* ADD - POST MAPPING */
    @GetMapping("/save/pc")
    public String showAddPcPage(Model model) {

        Pc newPc = new Pc();
        PcDetail newPcDetail = new PcDetail();

        model.addAttribute("pc", newPc);
        model.addAttribute("pcDetail", newPcDetail);

        return "/pc/add-pc";
    }

    @PostMapping("/save/pc")
    public String addNewPc(@ModelAttribute("pc") Pc pc, @ModelAttribute("pcDetail") PcDetail pcDetail) {

        if (pcDetail != null)
            pc.setPcDetail(pcDetail);
        else
            pc.setPcDetail(new PcDetail());


        pcService.savePc(pc);
        System.out.println("Pc: " + pc);
        System.out.println("Pc Details: " + pc.getPcDetail());

        return "redirect:/pcs";
    }


    /* UPDATE - PUT MAPPING */
    @GetMapping("/update/pc/{pcNumber}")
    public String showPcUpdate(Model model, @PathVariable("pcNumber") Long pcNumber) {

        Pc pc = pcService.findPcById(pcNumber);

        if (pc.getPcDetail() != null)
            model.addAttribute("pcDetail", pc.getPcDetail());
        else
            model.addAttribute("pcDetail", new PcDetail());

        model.addAttribute("pc", pc);

        return "/pc/update-pc";
    }

    @PutMapping("/update/pc/{pcNumber}")
    public String updatePc(@PathVariable("pcNumber") Long pcNumber) {

        Pc pc = pcService.findPcById(pcNumber);
        System.out.println(pc.toString());

        return "redirect:/pcs";
    }

    @PostMapping("/form/update/pc/{pcNumber}")
    public String updatePcGet(@ModelAttribute("pc") Pc pc, @ModelAttribute("pcDetail") PcDetail pcDetail) {

        System.out.println("\nUpdated Pc: " + pc.toString());
        System.out.println("Updated Pc Details: " + pcDetail.toString());

        pc.setPcDetail(pcDetail); // will save new Pc Detail in database ??
        pcService.updatePc(pc);

        return "redirect:/pcs";
    }


    /* PAGINATION */

    @GetMapping("/pcs") // pcs?page=0&size=5, pcs?page=0
    public String showPcs(Model model, @RequestParam(defaultValue = "0", name = "page") int pageNumber, @RequestParam(defaultValue = "5", name = "size") int pageSize) {

        List<Pc> pcs = pcService.findPcs(pageNumber, pageSize).getContent();
        int totalPcs = pcService.findAllPcs().size();
        int totalPages = pcService.findPcs(pageNumber, pageSize).getTotalPages();

        model.addAttribute("pcs", pcs);
        model.addAttribute("totalPcs", totalPcs);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageNumber", pageNumber);

        return "/pc/pcs";
    }

}
