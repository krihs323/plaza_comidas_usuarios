package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IUserPersistencePort;
import com.plazas.usuarios.domain.validations.UserValidations;
import com.plazas.usuarios.infraestructure.exception.UserAlreadyExistException;
import com.plazas.usuarios.infraestructure.exceptionhandler.ExceptionResponse;

import java.util.Optional;

public class UserUserCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUserCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveOwner(User user) {
        UserValidations.saveUser(user);
        user.setRole(Role.OWNER);
        user.setPassword(userPersistencePort.encodePassword(user.getPassword()));
        if (userPersistencePort.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(ExceptionResponse.USER_VALIDATION_EXIST.getMessage() + user.getEmail());
        }
        userPersistencePort.saveOwner(user);
    }

    @Override
    public void saveEmployee(User user) {
        UserValidations.saveEmployee(user);
        user.setRole(Role.EMPLOYEE);
        user.setPassword(userPersistencePort.encodePassword(user.getPassword()));
        Optional<User> userFound = userPersistencePort.findByEmail(user.getEmail());
        if (userFound.isPresent()) {
            throw new UserAlreadyExistException(ExceptionResponse.USER_VALIDATION_EXIST.getMessage() + user.getEmail());
        }
        userPersistencePort.saveOwner(user);
    }


    @Override
    public User getRolFromOwner(Long id) {
        return userPersistencePort.getRolFromOwner(id);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> userFoundByEmail = userPersistencePort.findByEmail(email);
        if (userFoundByEmail.isPresent()) {
            throw new UserAlreadyExistException(ExceptionResponse.USER_VALIDATION_EXIST.getMessage() + email);
        }
        return userFoundByEmail.orElseThrow();
    }

}
