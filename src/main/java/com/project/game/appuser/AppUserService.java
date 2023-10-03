package com.project.game.appuser;

import com.project.game.registration.token.ConfirmationToken;
import com.project.game.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found!";
    private final AppUserRepository appUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ConfirmationTokenService confirmationTokenService;

    //private final RegistrationService registrationService;

    //loadUserByEmail();
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    public void deleteUserById(Long id){
        appUserRepository.deleteUserById(id);
    }

    public AppUser singUpUser(AppUser appUser) {

        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

//        if (!userExists.get().getEnabled()){
//            //registrationService.sendBackupMail(appUser);
//            throw new IllegalStateException("Email not confirmed!");
//        }

        if(userExists)
            throw new IllegalStateException("Email already taken!");

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        //TOKEN
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), appUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return appUser;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
