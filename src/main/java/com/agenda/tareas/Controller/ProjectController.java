package com.agenda.tareas.Controller;

import com.agenda.tareas.Model.Project;
import com.agenda.tareas.Model.Task;
import com.agenda.tareas.Service.ProjectServiceImpl;
import com.agenda.tareas.Service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/")
    public String allProjects(Model model){
        model.addAttribute("projects", projectService.getAllProjects());
        return "index";
    }
    @GetMapping("/newProject")
    public String newTask(Model model){
        Project newProject = new Project();
        model.addAttribute("newProject", newProject);
        return "newProject";
    }
    @PostMapping("/project")
    public String project(@ModelAttribute("newProject") Project project){
        projectService.saveProject(project);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String updateProject(Model model, @PathVariable Long id){
        Project editProject = projectService.getProjectById(id);
        model.addAttribute("edit", editProject);
        return "editProject";
    }
    @PostMapping("/edit/{id}")
    public String saveEditProject(@ModelAttribute("edit") Project project, @PathVariable Long id){
        Project editProject = projectService.getProjectById(id);
        editProject.setTitle(project.getTitle());
        projectService.updateProject(editProject);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return "deleteConfirmation";
    }
    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id){
        Project project = projectService.getProjectById(id);
        List<Task> tasks = project.getTasks();

        for (Task task : tasks) {
            taskService.deleteTask(task.getId());
        }
        projectService.deleteProject(id);
        return "redirect:/";
    }
}
