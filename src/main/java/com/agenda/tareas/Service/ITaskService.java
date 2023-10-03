package com.agenda.tareas.Service;

import com.agenda.tareas.Model.Task;

import java.util.List;

public interface ITaskService {
    Task getTaskById(Long id);
    List<Task> getAllTask();
    Task saveTask(Task task);
    Task updateTask(Task task);
    void deleteTask(Long id);
}
