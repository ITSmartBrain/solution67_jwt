package app.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с задачами проектов
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}