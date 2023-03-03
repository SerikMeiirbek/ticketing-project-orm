package com.cydeo.repository;

import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    Optional<Task> findById(Long id);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.project.projectStatus <> 'COMPLETE'")
    int totalNonCompletedTasks(String projectCode);

//    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.project.projectStatus = 'COMPLETED'")
    @Query(value = "SELECT  COUNT(*) FROM tasks t JOIN projects p on t.project_id = p.id WHERE p.project_code = ?1 AND p.project_status = 'COMPLETE'" ,
            nativeQuery = true)
    int totalCompletedTasks(String projectCode);

    List<Task> findAllByProject(Project project);

    List<Task> findAllByTaskStatusIsNotAndAssignedEmployee(Status status, User assignedEmployee);

    List<Task> findAllByTaskStatusAndAssignedEmployee(Status status, User assignedEmployee);

    List<Task> findAllByAssignedEmployee(User assignedEmployee);
}
