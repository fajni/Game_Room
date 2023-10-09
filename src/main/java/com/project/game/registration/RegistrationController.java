package com.project.game.registration;

import com.project.game.appuser.AppUser;
import com.project.game.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = {"api/game/registration", "api/game/"})
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private AppUserService appUserService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

    @GetMapping("/sign_up")
    public ModelAndView signUp(){
        ModelAndView model = new ModelAndView("appUser/signup");
        AppUser newAppUser = new AppUser();
        model.addObject("appUser", newAppUser);
        return model;
    }

    @PostMapping("/saveAppUser")
    public ModelAndView saveAppUser(@ModelAttribute AppUser appUser) {
        registrationService.addAppUser(appUser);
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/account")
    public ModelAndView accountView(){
        ModelAndView model = new ModelAndView("appUser/Account");
        return model;
    }
}
