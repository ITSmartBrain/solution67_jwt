package app.service;

import app.domain.Project;
import app.domain.ProjectRepository;
import app.domain.Task;
import app.domain.TaskRepository;
import app.dto.TaskDto;
import app.exception.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void addTask(Long projectId, TaskDto taskDto) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("project not found"));
        Task task = modelMapper.map(taskDto, Task.class);
        project.getTasks().add(task);
        taskRepository.save(task);
        projectRepository.save(project);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public void updateTask(TaskDto taskDto) {
        Task task = taskRepository.findById(taskDto.getId()).orElseThrow(() -> new RuntimeException("task not found"));
        if(Objects.nonNull(taskDto.getDescription())){
            task.setDescription(taskDto.getDescription());
        }
        if(Objects.nonNull(taskDto.getTitle())){
            task.setTitle(taskDto.getTitle());
        }
        taskRepository.save(task);
    }
}
