package com.agenda.tareas.Service;

import com.agenda.tareas.Exceptions.ProjectNotFoundException;
import com.agenda.tareas.Model.Project;
import com.agenda.tareas.Repository.IProjectRepository;
import com.agenda.tareas.Repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService{
    @Autowired
    private IProjectRepository projectRepository;
    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Proyecto no encontrado"));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Proyecto no encontrado"));
        projectRepository.deleteById(id);
    }
}
