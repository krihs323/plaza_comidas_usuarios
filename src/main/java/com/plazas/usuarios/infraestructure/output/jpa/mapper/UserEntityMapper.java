package com.plazas.usuarios.infraestructure.output.jpa.mapper;

import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.infraestructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    @Mapping(source = "role", target = "role")
    UserEntity toEntity(User user);

    User toUser(UserEntity userEntity);

}
