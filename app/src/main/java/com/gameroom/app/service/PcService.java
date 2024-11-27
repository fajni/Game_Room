package com.gameroom.app.service;

import com.gameroom.app.dao.PcDAO;
import com.gameroom.app.model.Pc;
import com.gameroom.app.model.PcDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PcService {

    private final PcDAO pcDAO;

    @Autowired
    public PcService(PcDAO pcDAO) {
        this.pcDAO = pcDAO;
    }

    @Transactional
    public boolean savePc(Pc pc) {

        try {
            pcDAO.savePc(pc);
        } catch (Exception e) {
            System.err.println("Could NOT SAVE PC - " + pc.toString());
            return false;
        }
        return true;
    }

    @Transactional
    public boolean savePcWithPcDetails(Pc pc, PcDetail pcDetail) {

        pc.setPcDetail(pcDetail);

        try {
            pcDAO.savePc(pc);
        } catch (Exception e) {
            System.err.println("Could NOT save PC - " + pc.toString());
            return false;
        }

        return true;
    }

    public Pc findPcById(Long id) {

        Pc pc = null;

        try {
            pc = pcDAO.findPcById(id);
        } catch (Exception e) {
            System.err.println("Could NOT FIND Pc with " + id + " id!");
        }
        return pc;
    }

    public List<Pc> findAllPcs() {

        List<Pc> pcs = null;

        try{
            pcs = pcDAO.findAllPcs();
        } catch (Exception e) {
            System.err.println("Could NOT FIND Pcs");
        }

        return pcs;
    }

    @Transactional
    public boolean deletePcById(Long id) {

        try{
            pcDAO.deletePcById(id);
        } catch (Exception e) {
            System.err.println("Could NOT DELETE Pc with " + id + " id!");
            return false;
        }

        return true;
    }

    @Transactional
    public Pc updatePc(Pc pc) {

        try{
            pcDAO.updatePc(pc);
        } catch (Exception e) {
            System.err.println("Could NOT UPDATE Pc " + pc.toString());
            return null;
        }

        return pc;
    }

}
