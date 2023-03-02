package com.cydeo.service.serviceImpl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO taskDTO) {

        long projectId = projectRepository.findByProjectCode(taskDTO.getProject().getProjectCode()).getId();
        taskDTO.setTaskStatus(Status.OPEN);
        taskDTO.setAssignedDate(LocalDate.now());
        Task task = taskMapper.convertToEntity(taskDTO);
        task.getProject().setId(projectId);
        taskRepository.save(task);
    }

    @Override
    public void update(TaskDTO taskDTO) {

         Optional<Task> task = taskRepository.findById(taskDTO.getId());
         Task convertedTask = taskMapper.convertToEntity(taskDTO);

         if(task.isPresent()){
             convertedTask.setId(task.get().getId());
             convertedTask.setTaskStatus(task.get().getTaskStatus());
             convertedTask.setAssignedDate(task.get().getAssignedDate());
             convertedTask.setProject(task.get().getProject());
             taskRepository.save(convertedTask);
         }
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent()){
            return taskMapper.convertToDTO(task.get());
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.getById(id);
        task.setIsDeleted(true);
        taskRepository.save(task);
    }

    @Override
    public int totalNonCompletedTask(String projectCode) {
        return taskRepository.totalNonCompletedTasks(projectCode);
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);
    }
}
