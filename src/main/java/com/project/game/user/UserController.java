package com.project.game.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"api/game/user","api/game/user/"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView getUsers(){
        ModelAndView model = new ModelAndView("user/users");
        model.addObject("users", userService.getUsers());
        return model;

        //return new ModelAndView().addObject("users", userService.getUsers());
    }

    @GetMapping("/json")
    public List<User> getUsersJson(){
        return userService.getUsers();
    }

    @GetMapping("/{userNumber}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable Long userNumber){
        return new ResponseEntity<Optional<User>>(userService.findSingleUserByUserNumber(userNumber), HttpStatus.OK);
    }

    @GetMapping("/pc/{numberPc}") //dobijanje usera preko pcNumber
    public ResponseEntity<Optional<User>> getSingleUserByPcNumber(@PathVariable Long numberPc){
        return new ResponseEntity<Optional<User>>(userService.findSingleUserByPcNumber(numberPc), HttpStatus.OK);
    }

    @GetMapping("/removeUser") //removeUser?={userNumber}
    public ModelAndView removeUser(@RequestParam Long userNumber){
        userService.deleteUser(userNumber);
        return new ModelAndView("redirect:/api/game/user");
    }

    @GetMapping("/create_user")
    public ModelAndView submitUser(){
        ModelAndView model = new ModelAndView("user/create_user");//html file
        User newUser = new User();
        model.addObject("user", newUser);
        return model;
    }
    @PostMapping("/saveUser")
    public ModelAndView saveUser(@ModelAttribute User user){
        userService.addNewUser(user);
        return new ModelAndView("redirect:/api/game/user");
    }

    @GetMapping("/update_user")
    public ModelAndView updateUser(@RequestParam Long userNumber){
        ModelAndView model = new ModelAndView("user/update_user");
        User updateUser = userService.getUser(userNumber);
        model.addObject("user", updateUser);
        return model;
    }
    @PostMapping("/updateUser")
    public ModelAndView userUpdate(
            @RequestParam("userNumber") Long userNumber,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) Long numberPC
    ){
        userService.updateUser(userNumber, name, lastname, numberPC);
        return new ModelAndView("redirect:/api/game/user");
    }

    @PostMapping
    public void addNewPc(@RequestBody User user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "/delete/{userNumber}")
    public void deleteUser(@PathVariable("userNumber") Long userNumber){
        userService.deleteUser(userNumber);
    }

    @PutMapping(path = "{userNumber}")
    public void updateUser(@PathVariable("userNumber") Long userNumber,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String lastname,
                           @RequestParam(required = false) Long pcNumber){
        userService.updateUser(userNumber, name, lastname, pcNumber);
    }
}
