package com.agenda.tareas.Service;

import com.agenda.tareas.Model.Project;
import java.util.List;

public interface IProjectService {
    Project getProjectById(Long id);
    List<Project> getAllProjects();
    Project saveProject(Project project);
    Project updateProject(Project project);
    void deleteProject(Long id);
}
