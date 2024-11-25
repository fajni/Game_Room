package com.gameroom.app.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PcDetailDAO {

    private final EntityManager entityManager;

    @Autowired
    public PcDetailDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // No need for implementation... at least for now

}
