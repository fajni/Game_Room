package com.example.Registration.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    //@Query("")
    Optional<ConfirmationToken> findByToken(String token);

    Optional<ConfirmationToken> findByAppUserId(Long id);
}
