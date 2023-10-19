package com.agenda.tareas.Controller;

import com.agenda.tareas.Model.Project;
import com.agenda.tareas.Model.Task;
import com.agenda.tareas.Service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/listTask")
    public String allTask(Model model){
        model.addAttribute("tasks", taskService.getAllTask());
        return "listTask";
    }
    @GetMapping("/newTask")
    public String newTask(Model model){
        Task newTask = new Task();
        model.addAttribute("newTask", newTask);
        return "newTask";
    }
    @PostMapping("/task")
    public String task(@ModelAttribute("newTask") Task task){
        Project project = new Project();
        task.setCreationDate(LocalDateTime.now());
        taskService.saveTask(task);
        return "redirect:/tasks/listTask";
    }
    @GetMapping("/edit/{id}")
    public String updateTask(Model model, @PathVariable Long id){
        Task editTask = taskService.getTaskById(id);
        model.addAttribute("edit", editTask);
        model.addAttribute("endDate", editTask.getEndDate());
        return "editTask";
    }
    @PostMapping("/edit/{id}")
    public String saveEditTask(@ModelAttribute("edit") Task task, @PathVariable Long id){
        Task editTask = taskService.getTaskById(id);
        editTask.setTitle(task.getTitle());
        editTask.setDescription(task.getDescription());
        editTask.setEndDate(task.getEndDate());
        editTask.setCreationDate(LocalDateTime.now());
        taskService.updateTask(editTask);
        return "redirect:/tasks/listTask";
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/tasks/listTask";
    }
}
