package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {

        List<User> userList = userRepository.findAll(Sort.by("firstName"));

        //User to UserDTO conversion
        return userList.stream().map(userMapper::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public UserDTO findByUserName(String userName) {
        return null;
    }

    @Override
    public void save(UserDTO user) {
        User userEntity = userMapper.convertToEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public UserDTO update(UserDTO user) {
        return null;
    }

    @Override
    public void deleteByUserName(String username) {

    }
}
