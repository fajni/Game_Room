package com.gameroom.app.dao;

import com.gameroom.app.model.Pc;
import com.gameroom.app.model.PcDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PcDAO {

    private final EntityManager entityManager;

    @Autowired
    public PcDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public boolean savePc(Pc pc) {

        try {
            entityManager.persist(pc);
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
            entityManager.persist(pc);
        } catch (Exception e) {
            System.err.println("Could NOT save PC - " + pc.toString());
            return false;
        }

        return true;
    }

    public Pc findPcById(Long id) {

        Pc pc = null;

        try {
            pc = entityManager.find(Pc.class, id);
        } catch (Exception e) {
            System.err.println("Could NOT FIND Pc with " + id + " id!");
        }
        return pc;
    }

    public List<Pc> findAllPcs() {

        TypedQuery<Pc> query = entityManager.createQuery("SELECT p FROM Pc p", Pc.class);
        List<Pc> pcs = null;

        try {
            pcs = query.getResultList();
        } catch (Exception e) {
            System.err.println("Could NOT FIND Pcs");
        }

        return pcs;
    }

    @Transactional
    public boolean deletePcById(Long id) {

        try {
            Pc pc = entityManager.find(Pc.class, id);
            entityManager.remove(pc);
        } catch (Exception e) {
            System.err.println("Could NOT DELETE Pc with " + id + " id!");
            return false;
        }

        return true;
    }

    @Transactional
    public Pc updatePc(Pc pc) {

        try {
            entityManager.merge(pc);
        } catch (Exception e) {
            System.err.println("Could NOT UPDATE Pc " + pc.toString());
            return null;
        }

        return pc;
    }
}
