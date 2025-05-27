package com.plazas.usuarios.infraestructure.output.jpa.adapter;

import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IOwnerPersistencePort;
import com.plazas.usuarios.infraestructure.exception.UserAlreadyExistException;
import com.plazas.usuarios.infraestructure.exception.UserDoesNotExist;
import com.plazas.usuarios.infraestructure.exceptionhandler.ExceptionResponse;
import com.plazas.usuarios.infraestructure.output.jpa.entity.OwnerEntity;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.OwnerEntityMapper;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IOwnerRepository;

import java.util.Optional;

public class OwnerJpaAdapter implements IOwnerPersistencePort {

    private final IOwnerRepository ownerRepository;
    private final OwnerEntityMapper ownerEntityMapper;

    public OwnerJpaAdapter(IOwnerRepository ownerRepository, OwnerEntityMapper ownerEntityMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerEntityMapper = ownerEntityMapper;
    }


    @Override
    public void saveOwner(User user) {
        if (ownerRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(ExceptionResponse.USER_VALIDATION_EXIST.getMessage() + user.getEmail());
        }
        ownerRepository.save(ownerEntityMapper.toEntity(user));

    }

    @Override
    public User getRolFromOwner(Long id) {

        Optional<OwnerEntity> ownerFound = ownerRepository.findById(id);
        if(ownerFound.isEmpty()){
            throw new UserDoesNotExist(ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage());
        }

        return ownerEntityMapper.toOwner(ownerFound.orElseThrow());
    }

    @Override
    public User findByEmail(String email) {
        Optional<OwnerEntity> ownerFound = ownerRepository.findByEmail(email);
        if(ownerFound.isEmpty()){
            throw new UserDoesNotExist(ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage());
        }

        return ownerEntityMapper.toOwner(ownerFound.orElseThrow());
    }


}
