package com.plazas.usuarios.domain.api;

import com.plazas.usuarios.domain.model.User;

import java.util.Optional;

public interface IUserServicePort {

    void saveOwner(User user);

    void saveEmployee(User user);

    User getRolFromUser(Long id);

    User findByEmail(String email);

    void saveCustomer(User user);

    void updateUser(Long userId, User user);
}
