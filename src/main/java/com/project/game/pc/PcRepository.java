package com.project.game.pc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PcRepository extends JpaRepository<Pc, Long> {

    @Query("SELECT p FROM Pc p WHERE p.pcNumber=?1")
    Optional<Pc> findPcByNumber(Long pcNumber);

    @Query("SELECT p FROM Pc p WHERE p.title=?1")
    Optional<Pc> findPcByPcTitle(String title);

}
