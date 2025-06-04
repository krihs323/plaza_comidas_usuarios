package com.plazas.usuarios.infraestructure.security.mapper;

import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.infraestructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserDetailMapper {

    UserEntity toEntity(User user);

    User toOwner(UserEntity userEntity);

}
