package app.service;

import app.dto.ProjectDto;

import java.util.List;

/**
 * Сервис для работы с проектами
 */
public interface ProjectService {
    /**
     * Получить все проекты
     * @return список проектов
     */
    List<ProjectDto> findAll();

    /**
     * Найти проект по ID
     * @param id идентификатор проекта
     * @return данные проекта
     */
    ProjectDto findById(Long id);

    /**
     * Создать новый проект
     * @param projectDto данные проекта
     * @return созданный проект
     */
    ProjectDto create(ProjectDto projectDto);

    /**
     * Обновить проект
     * @param id идентификатор проекта
     * @param projectDto новые данные проекта
     * @return обновленный проект
     */
    ProjectDto update(Long id, ProjectDto projectDto);

    /**
     * Удалить проект
     * @param id идентификатор проекта
     */
    void delete(Long id);
}