package app.web;

import app.dto.TaskDto;
import app.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с задачами проектов
 */
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/project/{projectId}/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public void addTask(@PathVariable Long projectId, @RequestBody TaskDto taskDto){
        taskService.addTask(projectId, taskDto);
    }

    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }

    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public void updateTask(@RequestBody TaskDto taskDto){
        taskService.updateTask(taskDto);
    }






}