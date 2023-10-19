package com.agenda.tareas.Controller;

import com.agenda.tareas.Exceptions.ProjectNotFoundException;
import com.agenda.tareas.Exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> taskNotFound(TaskNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<String> projectNotFound(ProjectNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
