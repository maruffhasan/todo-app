package com.example.mytodo.service.imp;

import com.example.mytodo.model.Todo;
import com.example.mytodo.repository.TodoRepo;
import com.example.mytodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImp implements TodoService {

    @Autowired
    TodoRepo todoRepo;


    @Override
    public void addTodo(Todo todo, String username) {
        todoRepo.save(todo, username);
    }

    @Override
    public List<Todo> getAllTodo(String username) {
        return todoRepo.findAll(username);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoRepo.update(todo);
    }

    @Override
    public void deleteTodoById(int id) {
        todoRepo.delete(id);
    }

    @Override
    public Optional<Todo> getTodoById(long id) {
//        Todo todo = todoRepo.find(id);
//        return Optional.ofNullable(todo);
        return todoRepo.find(id);
    }
}
