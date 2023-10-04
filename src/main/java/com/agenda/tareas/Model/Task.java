package com.agenda.tareas.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "tareas")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "titulo", nullable = false, length = 45)
    private String tittle;
    @Column(name = "descripcion", nullable = false, length = 250)
    private String description;
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate startDate;
    @Column(name = "fecha_termino", nullable = false)
    private LocalDate endDate;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime creationDate;
}
