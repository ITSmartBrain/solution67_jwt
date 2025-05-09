package app.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторий для работы с проектами
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    /**
     * Найти проекты по статусу
     * @param status статус проекта
     * @return список проектов
     */
    List<Project> findByStatus(ProjectStatus status);

    /**
     * Найти проекты, у которых срок завершения раньше указанной даты
     * @param date дата для сравнения
     * @return список проектов
     */
    List<Project> findByEndDateBefore(LocalDate date);
}