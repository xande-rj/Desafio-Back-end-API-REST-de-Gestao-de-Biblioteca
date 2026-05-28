package com.biblioteca.mapper;

import com.biblioteca.domain.model.UserModel;
import com.biblioteca.dto.response.UserResponseDTO;
import com.biblioteca.dto.response.UserTokenResponseDTO;
import org.modelmapper.ModelMapper;

public class MapperUser {
    private final ModelMapper modelMapper = new ModelMapper();

    public UserTokenResponseDTO userToTokenDto (UserModel user){
        return this.modelMapper.map(user,UserTokenResponseDTO.class);
    }

    public UserResponseDTO userToDto (UserModel user){
        return this.modelMapper.map(user,UserResponseDTO.class);
    }
}
