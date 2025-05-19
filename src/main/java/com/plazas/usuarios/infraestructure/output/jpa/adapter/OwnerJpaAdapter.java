package com.plazas.usuarios.infraestructure.output.jpa.adapter;

import com.plazas.usuarios.domain.model.Owner;
import com.plazas.usuarios.domain.spi.IOwnerPersistencePort;
//import com.plazas.usuarios.infraestructure.exception.OwnerAlreadyExistException;
import com.plazas.usuarios.infraestructure.exception.OwnerAlreadyExistException;
import com.plazas.usuarios.infraestructure.output.jpa.entity.OwnerEntity;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.OwnerEntityMapper;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IOwnerRepository;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
public class OwnerJpaAdapter implements IOwnerPersistencePort {

    private final IOwnerRepository ownerRepository;
    private final OwnerEntityMapper ownerEntityMapper;

    public OwnerJpaAdapter(IOwnerRepository ownerRepository, OwnerEntityMapper ownerEntityMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerEntityMapper = ownerEntityMapper;
    }


    @Override
    public void saveOwner(Owner owner) {
        if (ownerRepository.findByEmail(owner.getEmail()).isPresent()) {
            throw new OwnerAlreadyExistException("El propietario ya fue creado con el correo: " + owner.getEmail());
        }
        ownerRepository.save(ownerEntityMapper.toEntity(owner));

    }
}
