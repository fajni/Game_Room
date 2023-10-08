package com.example.Registration.registration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"api/registration", "api/registration/", "api/"})
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

//    public RegistrationController(RegistrationService registrationService) {
//        this.registrationService = registrationService;
//    }

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping("confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
