package com.project.game.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    //@Query
    Optional<ConfirmationToken> findByToken(String token);

    //@Query("SELECT t FROM ConfirmationToken t WHERE t.app_user_id=?1")
    Optional<ConfirmationToken> findTokenByAppUserId(Long app_user_id);
}
