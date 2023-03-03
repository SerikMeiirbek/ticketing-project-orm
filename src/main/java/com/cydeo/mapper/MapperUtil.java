package com.cydeo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil{
    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T convert(Object objToBeConverted, T convertedObject){
        return modelMapper.map(objToBeConverted, (Type) convertedObject.getClass());
    }

//    public <T> T convertToEntity(Object objToBeConverted, T convertedObject){
//        return modelMapper.map(objToBeConverted, (Type) convertedObject.getClass());
//    }
//
//    public <T> T convertToDTO(Object objToBeConverted, T convertedObject){
//        return modelMapper.map(objToBeConverted, (Type) convertedObject.getClass());
//    }


}
