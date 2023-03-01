package com.cydeo.mapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //convertToEntity
    public User convertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    //convertToDTO
    public UserDTO convertToDto(User user){
        return modelMapper.map(user, UserDTO.class);
    }


}
