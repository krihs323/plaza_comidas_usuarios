package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.domain.api.IOwnerServicePort;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IOwnerPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class OwnerUserCase implements IOwnerServicePort {

    private final IOwnerPersistencePort ownerPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public OwnerUserCase(IOwnerPersistencePort ownerPersistencePort, PasswordEncoder passwordEncoder) {
        this.ownerPersistencePort = ownerPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveOwner(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ownerPersistencePort.saveOwner(user);
    }

    @Override
    public User getRolFromOwner(Long id) {
        return ownerPersistencePort.getRolFromOwner(id);
    }

    @Override
    public User findByEmail(String email) {
        return ownerPersistencePort.findByEmail(email);
    }


}
