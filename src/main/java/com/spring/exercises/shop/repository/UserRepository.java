package com.spring.exercises.shop.repository;

import com.spring.exercises.shop.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private List<UserDto> userList;

    @Autowired
    public UserRepository() {
        this.userList = new ArrayList<>();
    }

    public void addUserToList(UserDto userDto) {
        UserDto user = new UserDto();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setAddDate(new Date());
        userList.add(user);
    }

    public List<UserDto> findAllUsers(){

        return userList;
        //return StreamSupport.stream(userList.spliterator(),false).collect(Collectors.toList());
    }

    @PostMapping("/deleteuser")
    public String deleteUser(@ModelAttribute("user") UserDto userDto) {
        userList.remove(userDto);
        return "userdeleteresult";
    }


    public void deleteUserById(int userId) {
        userList.remove(userId);
    }

    public List<UserDto> findUserByName(String name) {
        return userList.stream().filter(x->x.getName().equals(name)).collect(Collectors.toList());
    }

    public List<UserDto> sortByName() {
        return userList.stream().sorted().collect(Collectors.toList());
    }
}
