package com.plazas.usuarios.infraestructure.security.mapper;

import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.infraestructure.output.jpa.entity.OwnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserDetailMapper {

    //@Mapping(source = "role", target = "role")
    OwnerEntity toEntity(User user);

    User toOwner(OwnerEntity ownerEntity);

}
