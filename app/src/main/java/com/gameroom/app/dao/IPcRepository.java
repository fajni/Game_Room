package com.gameroom.app.dao;

import com.gameroom.app.model.Pc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPcRepository extends PagingAndSortingRepository<Pc, Long> {

    // find Pcs
    public Page<Pc> findAll(Pageable pageable);

}
