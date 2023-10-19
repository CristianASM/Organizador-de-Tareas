package com.agenda.tareas.Exceptions;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String message){
        super(message);
    }
}
