package com.gameroom.app.dao;

import com.gameroom.app.model.PcDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PcDetailDAO {

    private final EntityManager entityManager;

    @Autowired
    public PcDetailDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public PcDetail findPcDetailById(int id) {

        PcDetail pcDetail = null;

        try {
            pcDetail = entityManager.find(PcDetail.class, id);
        } catch (Exception e) {
            System.err.println("Could NOT FIND Pc Details with " + id + " id!");
        }

        return pcDetail;
    }

    @Transactional
    public PcDetail updatePcDetail(PcDetail pcDetail) {

        try {
            entityManager.merge(pcDetail);
        } catch (Exception e) {
            System.err.println("Could NOT UPDATE Pc Details: " + pcDetail.toString());
            return null;
        }

        return pcDetail;

    }

}
