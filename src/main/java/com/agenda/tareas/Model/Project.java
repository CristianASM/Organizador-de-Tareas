package com.agenda.tareas.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "proyectos")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "titulo", nullable = false, length = 45)
    private String title;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Task> tasks;
    
}
