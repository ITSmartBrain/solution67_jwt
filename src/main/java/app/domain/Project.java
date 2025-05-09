package app.domain;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность проекта в системе управления проектами
 */
@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    /**
     * Уникальный идентификатор проекта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название проекта
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Описание проекта
     */
    @Column(length = 500)
    private String description;

    /**
     * Дата начала проекта
     */
    @Column(nullable = false)
    private LocalDate startDate;

    /**
     * Дата завершения проекта
     */
    private LocalDate endDate;

    /**
     * Статус проекта (ACTIVE, COMPLETED, CANCELLED)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    /**
     * Список задач в проекте
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}

