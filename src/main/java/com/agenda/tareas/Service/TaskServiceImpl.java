package com.agenda.tareas.Service;

import com.agenda.tareas.Exceptions.TaskNotFoundException;
import com.agenda.tareas.Model.Task;
import com.agenda.tareas.Repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService{
    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Tarea no encontrada"));
    }
    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }
    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
