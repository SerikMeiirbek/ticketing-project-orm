package com.cydeo.repository;

import com.cydeo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    Optional<Task> findById(Long id);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.project.projectStatus <> 'COMPLETED'")
    int totalNonCompletedTasks(String projectCode);

//    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.project.projectStatus = 'COMPLETED'")

    @Query(value = "SELECT  COUNT(*) FROM tasks t JOIN projects p on t.project_id = p.id WHERE p.project_code = ?1 AND p.project_status = 'COMPLETE'" ,
            nativeQuery = true)
    int totalCompletedTasks(String projectCode);
}
