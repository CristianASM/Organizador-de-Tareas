package com.agenda.tareas.Repository;

import com.agenda.tareas.Model.Project;
import com.agenda.tareas.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
}
