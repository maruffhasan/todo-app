package com.example.mytodo.service;

import com.example.mytodo.DTO.UserDTO;
import com.example.mytodo.model.User;

import java.util.List;
import java.util.Map;


public interface UserService {

    void signUp(User user);

    Map<String, String> getName(String username);

    List<UserDTO> getUsers();

    void deleteUser(int id);
}

