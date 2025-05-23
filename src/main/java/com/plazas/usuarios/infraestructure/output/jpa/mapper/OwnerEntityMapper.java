package com.plazas.usuarios.infraestructure.output.jpa.mapper;

import com.plazas.usuarios.domain.model.Owner;
import com.plazas.usuarios.infraestructure.output.jpa.entity.OwnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OwnerEntityMapper {

    OwnerEntity toEntity(Owner owner);

    Owner toOwner(OwnerEntity ownerEntity);

}
