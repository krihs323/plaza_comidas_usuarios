package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.domain.exception.UserDoesNotExist;
import com.plazas.usuarios.domain.exception.UserValidationException;
import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IUserPersistencePort;
import com.plazas.usuarios.domain.validations.UserValidations;
//TODO manejarlos desde el dominio y no desde la infra - Ajustado
import com.plazas.usuarios.domain.exception.UserAlreadyExistException;
import com.plazas.usuarios.domain.exception.ExceptionResponse;
import java.util.Optional;

public class UserUserCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUserCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveOwner(User user) {
        UserValidations.saveUser(user);
        //TODO Del flujo de save customer no lo setea a cliente - Ajustado
        //TODO Solo el propietario crea empleado de su empresa - Ajustado, al crear el restaurante se asocia
        user.setRole(Role.OWNER);
        user.setPassword(userPersistencePort.encodePassword(user.getPassword()));
        if (userPersistencePort.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(ExceptionResponse.USER_VALIDATION_EXIST.getMessage() + user.getEmail());
        }
        userPersistencePort.save(user);
    }

    @Override
    public void saveEmployee(User user) {
        UserValidations.saveUser(user);

        Optional<User> userFound = userPersistencePort.findByEmail(user.getEmail());
        if (userFound.isPresent()) {
            throw new UserAlreadyExistException(ExceptionResponse.USER_VALIDATION_EXIST.getMessage() + user.getEmail());
        }
        user.setRole(Role.EMPLOYEE);
        user.setPassword(userPersistencePort.encodePassword(user.getPassword()));
        //TODO nombramiento como solamente save - Ajustado
        User userAuth = userPersistencePort.getUseAuth();
        user.setIdRestaurantEmployee(userAuth.getIdRestaurantEmployee());
        userPersistencePort.save(user);
    }


    @Override
    public User getRolFromUser(Long id) {
        Optional<User> userFound = userPersistencePort.getRolFromUser(id);
        if(userFound.isEmpty()){
            throw new UserDoesNotExist(ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage());
        }
        return userFound.orElseThrow();
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> userFoundByEmail = userPersistencePort.findByEmail(email);
        if (userFoundByEmail.isPresent()) {
            throw new UserAlreadyExistException(ExceptionResponse.USER_VALIDATION_EXIST.getMessage() + email);
        }
        return userFoundByEmail.orElseThrow(() -> new UserValidationException(ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage()));
    }

    @Override
    public void saveCustomer(User user) {

        UserValidations.saveUser(user);
        user.setRole(Role.CUSTOMER);
        user.setPassword(userPersistencePort.encodePassword(user.getPassword()));
        if (userPersistencePort.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(ExceptionResponse.USER_VALIDATION_EXIST.getMessage() + user.getEmail());
        }
        userPersistencePort.save(user);
    }

    @Override
    public void updateUser(Long userId, User user) {

        Optional<User> userFound = userPersistencePort.getRolFromUser(userId);
        if (userFound.isEmpty()) {
            throw new UserAlreadyExistException(ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage());
        }
        User userToUpdate = userFound.get();
        userToUpdate.setIdRestaurantEmployee(user.getIdRestaurantEmployee());

        userPersistencePort.save(userToUpdate);
    }

}
