package com.project.game.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository tokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        tokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return tokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public void deleteConfirmationTokenByToken(String token) {
        tokenRepository.deleteTokenByToken(token);
    }

    public Optional<ConfirmationToken> getTokenByAppUserId(Long app_user_id){
        return tokenRepository.findTokenByAppUserId(app_user_id);
    }
}
