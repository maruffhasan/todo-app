package com.example.mytodo.controller;

import com.example.mytodo.DTO.UserDTO;
import com.example.mytodo.model.User;
import com.example.mytodo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/me")
    public Map<String, String> getMe(Authentication auth) {
        String username = auth.getName();
        return userService.getName(username);
    }

    @PostMapping("/api/user/signup")
    public void signup(@RequestBody User user) {
        userService.signUp(user);
    }

    @GetMapping("/api/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("/api/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
