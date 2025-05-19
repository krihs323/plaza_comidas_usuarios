package com.plazas.usuarios.infraestructure.output.jpa.repository;

import com.plazas.usuarios.infraestructure.output.jpa.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOwnerRepository extends JpaRepository<OwnerEntity, Long> {

    Optional<OwnerEntity> findByEmail(String email);
}
