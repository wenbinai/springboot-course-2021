package com.example.redisexamples.controller;

import com.example.redisexamples.dto.UserDTO;
import com.example.redisexamples.entity.User;
import com.example.redisexamples.service.UserDTOService;
import com.example.redisexamples.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MyController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOService userDTOService;

    @GetMapping("users/{uid}")
    public User getUser(@PathVariable long uid) {
        return userService.getUser(uid);
    }
    @GetMapping("users")
    public List<User> listUsers() {
        return userService.listUsers();
    }
    @PatchMapping("users")
    public User patchUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("users/{uid}")
    public void delUser(@PathVariable long uid) {
        userService.delUser(uid);
    }

    @GetMapping("userdtos/{uid}")
    public UserDTO getUserDTO(@PathVariable long uid) {
        return userDTOService.getUserDTO(uid);
    }
    @PostMapping("userdto/{uid}")
    public void postUser(@PathVariable long uid) {
        userDTOService.updateUserDTO(uid);
    }
}
