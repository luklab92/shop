package com.spring.exercises.shop.controller;

import com.spring.exercises.shop.model.UserDto;
import com.spring.exercises.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController{
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/adduser")
    public ModelAndView addUser(Model model) {
        return new ModelAndView("userForm","userToInsert",new UserDto());
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute UserDto userDto){
        userRepository.addUserToList(userDto);
        return "userSaveResult";
    }

    @PostMapping("/goback")
    public String goBack() {
        return "index";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<UserDto> list = userRepository.findAllUsers();
        model.addAttribute("users", list);
        System.out.println(list);
        return "showAllUsers";
    }

    @PostMapping("/deleteuser")
    public String deleteUser(@ModelAttribute("user") UserDto userDto) {

        userRepository.deleteUser(userDto);
        return "userdeleteresult";
    }

    @GetMapping("/finduser/{name}")
    public String addUser(Model model, @PathVariable("name") String name) {
        List<UserDto> list = userRepository.findUserByName(name);
        model.addAttribute("find", list);
        System.out.println(list);
        return "finduserresult";
    }


    @PostMapping("/finduser")
    public String findUser(@ModelAttribute String name) {
        userRepository.findUserByName(name);
        return "finduserresult";
    }

}
