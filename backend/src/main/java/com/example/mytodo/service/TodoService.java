package com.example.mytodo.service;

import com.example.mytodo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    void addTodo(Todo todo, String username);
    List<Todo> getAllTodo(String username);
    void updateTodo(Todo todo);
    void deleteTodoById(int id);

    Optional<Todo> getTodoById(long id);
}