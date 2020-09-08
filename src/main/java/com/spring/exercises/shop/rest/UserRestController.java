package com.spring.exercises.shop.rest;

import com.spring.exercises.shop.model.UserDto;
import com.spring.exercises.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @GetMapping("/users")
    public List<UserDto> showAllUsers() {
        System.out.println(userRepository.findAllUsers());
        return userRepository.findAllUsers();
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute UserDto userDto){
        userRepository.addUserToList(userDto);
        return "userSaveResult";
    }
    @GetMapping("/deleteuser/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userRepository.deleteUserById(userId);
    }

}
