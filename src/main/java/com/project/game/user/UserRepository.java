package com.project.game.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userNumber=?1")
    Optional<User> findUserByNumber(Long userNumber);

    @Query("SELECT u FROM User u WHERE u.numberPC=?1")
    Optional<User> findUserByPcNumber(Long numberPc);
}
