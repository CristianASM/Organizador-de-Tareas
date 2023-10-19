package com.agenda.tareas.Controller;

import com.agenda.tareas.Model.Project;
import com.agenda.tareas.Service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectService;

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
    public String deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return "redirect:/";
    }
}