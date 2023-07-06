package com.najjar.taskmanagementsystem.model.dto;

import com.najjar.taskmanagementsystem.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

         modelMapper.typeMap(User.class, UserDTO.class)
                 .addMapping(User::getName, UserDTO::setName)
                 .addMapping(User::getEmail, UserDTO::setEmail)
                 .addMapping(User::getRole, UserDTO::setRole)
                 .addMapping(User::getTeamId, UserDTO::setTeamId);

        return modelMapper;
    }
}
