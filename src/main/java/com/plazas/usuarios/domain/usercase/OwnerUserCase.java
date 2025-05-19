package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.domain.api.IOwnerServicePort;
import com.plazas.usuarios.domain.model.Owner;
import com.plazas.usuarios.domain.spi.IOwnerPersistencePort;
import com.plazas.usuarios.infraestructure.output.jpa.adapter.OwnerJpaAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

public class OwnerUserCase implements IOwnerServicePort {


    private final IOwnerPersistencePort ownerPersistencePort;
    private final PasswordEncoder passwordEncoder;


    public OwnerUserCase(IOwnerPersistencePort ownerPersistencePort, PasswordEncoder passwordEncoder) {
        this.ownerPersistencePort = ownerPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    //Comunicar lo que pasa por el dominio y va hacia la persistencia

    @Override
    public void saveOwner(Owner owner) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        ownerPersistencePort.saveOwner(owner);
    }
}
