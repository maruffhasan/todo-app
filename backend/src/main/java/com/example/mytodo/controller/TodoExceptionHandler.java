package com.example.mytodo.controller;

import com.example.mytodo.exception.ErrorResponse;
import com.example.mytodo.exception.TodoNotFoundExeception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoExceptionHandler {

    @ExceptionHandler(TodoNotFoundExeception.class)
    public ResponseEntity<ErrorResponse> handleTodoNotFoundExeception(TodoNotFoundExeception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
}
