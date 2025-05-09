package app.service;

import app.dto.TaskDto;

public interface TaskService {
    /**
     * Добавление новой задачи к проекту
     * @param projectId id проекта
     * @param taskDto dto новой задачи
     */
    void addTask(Long projectId, TaskDto taskDto);

    /**
     * Удаление задачи
     * @param taskId
     */
    void deleteTask(Long taskId);

    /**
     * Обновление задачи
     * @param taskDto
     */
    void updateTask(TaskDto taskDto);
}
