package com.app.fullstackbackend.controller;

import com.app.fullstackbackend.exception.UserNotFoundException;
import com.app.fullstackbackend.model.UserClass;
import com.app.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public UserClass newUser(@RequestBody UserClass newUserClass){
        return userRepository.save(newUserClass);
    }

    @GetMapping("/getUsers")
    public List<UserClass> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/getUsers/{id}")
    public UserClass getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/updateUsers/{id}")
    public UserClass updateUser(@RequestBody UserClass newUserClass, @PathVariable Long id){
        return userRepository.findById(id).map(userClass -> {
           userClass.setUsername(newUserClass.getUsername());
            userClass.setName(newUserClass.getName());
            userClass.setEmail(newUserClass.getEmail());
            return userRepository.save(userClass);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/deleteUsers/{id}")
    public String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted successfully!";
    }

}
