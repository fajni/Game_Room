package com.project.game.registration.token;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository tokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveConfirmationToken(ConfirmationToken token) {
        tokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        ConfirmationToken confirmationToken = tokenRepository.findByToken(token).get();
        confirmationToken.setConfirmedAt(LocalDateTime.now());
    }

    public Optional<ConfirmationToken> getTokenByAppUserId(Long app_user_id){
        return tokenRepository.findTokenByAppUserId(app_user_id);
    }
}
