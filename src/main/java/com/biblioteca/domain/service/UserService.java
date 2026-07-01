package com.biblioteca.domain.service;

import com.biblioteca.domain.enuns.Roles;
import com.biblioteca.domain.model.LoanModel;
import com.biblioteca.domain.model.UserModel;
import com.biblioteca.domain.repository.UserRepository;
import com.biblioteca.dto.request.UserAuthRequestDTO;
import com.biblioteca.dto.request.UserRequestDTO;
import com.biblioteca.dto.request.UserUpdateDTO;

import com.biblioteca.dto.response.LoansResponseDTO;
import com.biblioteca.dto.response.UserResponseDTO;
import com.biblioteca.dto.response.UserTokenResponseDTO;
import com.biblioteca.exception.ResourceNotFoundException;

import com.biblioteca.mapper.MapperLoan;
import com.biblioteca.mapper.MapperUser;
import com.biblioteca.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final MapperUser mapperUser = new MapperUser();
    private final MapperLoan mapperLoan = new MapperLoan();

    @Autowired
private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public UserTokenResponseDTO saveUser(UserRequestDTO data) {
        if (this.repository.findByEmailUser(data.getEmail()).isPresent()) {
            throw new ResourceNotFoundException("email ja cadastrado");
        }
        UserModel user = new UserModel();
        user.setNameUser(data.getName());
        user.setEmailUser(data.getEmail());

        user.setPasswordUser(passwordEncoder.encode(data.getPassword()));
        user.setDateOfBirth(data.getDate_of_birth());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRemovedUser(false);
        if(data.getRoles()!=null) {
            user.setRoleUser(Roles.valueOf(data.getRoles().toUpperCase()));
        }else {
            user.setRoleUser(Roles.READER);

        }

        UserModel newUser = this.repository.save(user);
        return new UserTokenResponseDTO(jwtUtils.generateToken(newUser.getRoleUser()));
    }
public UserTokenResponseDTO authUser(UserAuthRequestDTO data){
    UserModel user = this.repository.findByEmailUser(data.getEmail()).orElseThrow(() ->  new ResourceNotFoundException("Usuario nao encontrado"));

    if(!passwordEncoder.matches(data.getPassword(), user.getPasswordUser())){
        throw new ResourceNotFoundException("senha errada");
    }

    return new UserTokenResponseDTO(jwtUtils.generateToken(user.getRoleUser()));

}
    public UserResponseDTO getUseById(Long id) {
        UserModel user = this.repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Usuario nao encontrado"));
        return this.mapperUser.userToDto(user);
    }

    public UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        UserModel user = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));
        user.setNameUser(userUpdateDTO.getName());
        user.setEmailUser(userUpdateDTO.getEmail());

        return this.mapperUser.userToDto(this.repository.save(user));
    }

    public List<LoansResponseDTO> historicalLoans(Long id){
        return mapperLoan.listLoanResponse(this.repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user nao encontrado")).getHistorical());
    }

}
