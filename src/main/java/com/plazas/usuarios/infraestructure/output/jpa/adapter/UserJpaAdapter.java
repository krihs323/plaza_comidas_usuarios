package com.plazas.usuarios.infraestructure.output.jpa.adapter;

import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IUserPersistencePort;
import com.plazas.usuarios.infraestructure.exception.UserAlreadyExistException;
import com.plazas.usuarios.infraestructure.exception.UserDoesNotExist;
import com.plazas.usuarios.infraestructure.exceptionhandler.ExceptionResponse;
import com.plazas.usuarios.infraestructure.output.jpa.entity.UserEntity;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IUserRepository;

import java.util.Optional;

public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserJpaAdapter(IUserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }


    @Override
    public void saveOwner(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(ExceptionResponse.USER_VALIDATION_EXIST.getMessage() + user.getEmail());
        }
        userRepository.save(userEntityMapper.toEntity(user));

    }

    @Override
    public User getRolFromOwner(Long id) {

        Optional<UserEntity> ownerFound = userRepository.findById(id);
        if(ownerFound.isEmpty()){
            throw new UserDoesNotExist(ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage());
        }

        return userEntityMapper.toOwner(ownerFound.orElseThrow());
    }

    @Override
    public User findByEmail(String email) {
        Optional<UserEntity> ownerFound = userRepository.findByEmail(email);
        if(ownerFound.isEmpty()){
            throw new UserDoesNotExist(ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage());
        }

        return userEntityMapper.toOwner(ownerFound.orElseThrow());
    }


}
