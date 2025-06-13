package com.example.mytodo.controller;

import com.example.mytodo.exception.TodoNotFoundExeception;
import com.example.mytodo.model.Todo;
import com.example.mytodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    TodoService todoService;

    @PostMapping("/api/todo")
    public void addTodo (@RequestBody Todo todo, Authentication auth) {
        todoService.addTodo(todo, auth.getName());
    }

    @GetMapping("/api/todo")
    @PreAuthorize("hasRole('USER')")
    public List<Todo> getAllTodo(Authentication auth) {
        return todoService.getAllTodo(auth.getName());
    }

    @GetMapping("/api/todo/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable long id, Authentication auth) throws TodoNotFoundExeception {
//        Optional<Todo> todo = todoService.getTodoById(id);
//        if (todo.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK).body(todo.get());
//        }
//
//        throw new TodoNotFoundExeception("Todo not found: " + id);
        Todo todo = todoService.getTodoById(id).orElseThrow(
                ()-> new TodoNotFoundExeception("Todo not found with id : " + id)
        );
        return ResponseEntity.status(HttpStatus.OK).body(todo);
    }

    @PutMapping("/api/todo/{id}")
    public void updateTodo (@PathVariable int id, @RequestBody Todo todo) {
        todo.setId(id);
        todoService.updateTodo(todo);
    }

    @DeleteMapping("/api/todo/{id}")
    public void deleteTodoById(@PathVariable int id) {
        todoService.deleteTodoById(id);
    }
}
