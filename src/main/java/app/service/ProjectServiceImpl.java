package app.service;

import app.domain.Project;
import app.domain.ProjectRepository;
import app.domain.ProjectStatus;
import app.dto.ProjectDto;
import app.exception.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для работы с проектами
 */
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDto findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id="+id+" not found"));
        return convertToDto(project);
    }

    @Override
    @Transactional
    public ProjectDto create(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        project.setStatus(ProjectStatus.ACTIVE);
        Project savedProject = projectRepository.save(project);
        return convertToDto(savedProject);
    }

    @Override
    @Transactional
    public ProjectDto update(Long id, ProjectDto projectDto) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id="+id+" not found"));

        modelMapper.map(projectDto, existingProject);
        Project updatedProject = projectRepository.save(existingProject);
        return convertToDto(updatedProject);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project with id="+id+" not found");
        }
        projectRepository.deleteById(id);
    }

    private ProjectDto convertToDto(Project project) {
        ProjectDto dto = modelMapper.map(project, ProjectDto.class);
        dto.setStatus(project.getStatus().name());
        return dto;
    }
}