package com.cydeo.service.serviceImpl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class  UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProjectService projectService;
    private final TaskService taskService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, @Lazy ProjectService projectService, TaskService taskService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return  userRepository.findAll(Sort.by("firstName")).stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String userName) {
       return userMapper.convertToDto(userRepository.findByUserName(userName));
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.convertToEntity(userDTO));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {

        User user = userRepository.findByUserName(userDTO.getUserName());

        User updatedUser = userMapper.convertToEntity(userDTO);

        if(updatedUser.getId() == null){
            updatedUser.setId(user.getId());
        }

        userRepository.save(updatedUser);

        return findByUserName(userDTO.getUserName());
    }

    @Override
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    @Override
    public void delete(String userName) {
        User user = userRepository.findByUserName(userName);

        if(checkIfUserCanBeDeleted(user)){
            user.setIsDeleted(true);
            user.setUserName(user.getUserName() + "-" + user.getId());
            userRepository.save(user);
        }

    }

    private boolean checkIfUserCanBeDeleted(User user){
        switch(user.getRole().getDescription()){
            case "Manager" :
                List<ProjectDTO> projectDTOList = projectService.readAllByAssignedManager(user);
                return  projectDTOList.size() == 0 ? true : false;
            case "Employee":
                List<TaskDTO> taskDTOList = taskService.readAllByAssignedEmployee(user);
                return  taskDTOList.size() == 0 ? true : false;
            default:
                return true;
        }
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> users = userRepository.findAllByRoleDescriptionIgnoreCase(role);
        List<UserDTO> userDtoList = users.stream().map(userMapper::convertToDto).collect(Collectors.toList());
        return  userDtoList;

    }


}
