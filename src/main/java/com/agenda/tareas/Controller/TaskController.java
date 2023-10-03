package com.agenda.tareas.Controller;

import com.agenda.tareas.Model.Task;
import com.agenda.tareas.Service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/")
    public String allTask(Model model){
        model.addAttribute("tasks", taskService.getAllTask());
        return "index";
    }
    @GetMapping("/newTask")
    public String newTask(Model model){
        Task newTask = new Task();
        model.addAttribute("newTask", newTask);
        return "newTask";
    }
    @PostMapping("/task")
    public String task(@ModelAttribute("newTask") Task task){
        task.setDate(LocalDateTime.now());
        taskService.saveTask(task);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String updateTask(Model model, @PathVariable Long id){
        model.addAttribute("edit", taskService.getTaskById(id));
        return "editTask";
    }
    @PostMapping("/edit/{id}")
    public String saveEditTask(@ModelAttribute("edit") Task task, @PathVariable Long id){
        Task editTask = taskService.getTaskById(id);
        editTask.setTittle(task.getTittle());
        editTask.setDescription(task.getDescription());
        editTask.setDate(LocalDateTime.now());
        taskService.updateTask(editTask);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/";
    }
}
