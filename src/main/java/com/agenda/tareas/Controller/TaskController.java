package com.agenda.tareas.Controller;

import com.agenda.tareas.Model.Project;
import com.agenda.tareas.Model.Task;
import com.agenda.tareas.Service.ProjectServiceImpl;
import com.agenda.tareas.Service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private ProjectServiceImpl projectService;

    @GetMapping("/listTask/{projectId}")
    public String viewTasks(@PathVariable Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        List<Task> tasks = taskService.getTasksByProject(project);
        model.addAttribute("project", project);
        model.addAttribute("tasks", tasks);
        return "listTask";
    }
    @GetMapping("/newTask")
    public String newTask(@RequestParam("projectId") Long projectId, Model model){
        Task newTask = new Task();
        newTask.setProject(new Project());
        model.addAttribute("currentProject", projectId);
        model.addAttribute("newTask", newTask);
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "newTask";
    }
    @PostMapping("/task")
    public String task(@ModelAttribute("newTask") Task task, @RequestParam("projectId") Long projectId){
        Project project = projectService.getProjectById(projectId);
        if (project != null) {
            project.addTask(task);
            projectService.saveProject(project);
        }
        task.setCreationDate(LocalDateTime.now());
        taskService.saveTask(task);
        return "redirect:/tasks/listTask/" + projectId;
    }
    @GetMapping("/edit/{id}")
    public String updateTask(Model model, @PathVariable Long id){
        Task editTask = taskService.getTaskById(id);
        model.addAttribute("edit", editTask);
        model.addAttribute("endDate", editTask.getEndDate());
        return "editTask";
    }
    @PostMapping("/edit/{id}")
    public String saveEditTask(@ModelAttribute("edit") Task editedTask, @PathVariable Long id) {
        Task taskToEdit = taskService.getTaskById(id);
        if (taskToEdit != null) {
            taskToEdit.setDescription(editedTask.getDescription());
            taskToEdit.setEndDate(editedTask.getEndDate());
            taskToEdit.setCreationDate(LocalDateTime.now());
            taskService.updateTask(taskToEdit);
        }
        assert taskToEdit != null;
        return "redirect:/tasks/listTask/" + taskToEdit.getProject().getId();
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        Task task = taskService.getTaskById(id);
        long projectId = task.getProject().getId();
        taskService.deleteTask(id);
        return "redirect:/tasks/listTask/" + projectId;
    }
}
