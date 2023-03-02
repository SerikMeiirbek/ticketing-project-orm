package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Project;

import java.util.List;

public interface ProjectService {

    ProjectDTO getByProjectCode(String Code);
    List<ProjectDTO> listAllProjects();
    void save(ProjectDTO dto);
    void update(ProjectDTO dto);
    void delete(String code);
    void complete(String projectCode);
    List<ProjectDTO> listAllProjectsByManager();
}
