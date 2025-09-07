package com.gameroom.app.dao;

import com.gameroom.app.model.Pc;
import com.gameroom.app.model.PcDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PcDAO {

    private final EntityManager entityManager;

    @Autowired
    public PcDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void savePc(Pc pc) {

        entityManager.persist(pc);
    }

    public Pc findPcById(Long id) {

        return entityManager.find(Pc.class, id);
    }

    public List<Pc> findAllPcs() {

        TypedQuery<Pc> query = entityManager.createQuery("SELECT p FROM Pc p", Pc.class);
        return query.getResultList();
    }

    public void deletePcById(Long id) {

        Pc pc = entityManager.find(Pc.class, id);
        entityManager.remove(pc);
    }

    public void updatePc(Pc pc) {

        entityManager.merge(pc);
    }
}
