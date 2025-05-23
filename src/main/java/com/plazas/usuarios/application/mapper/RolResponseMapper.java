package com.plazas.usuarios.application.mapper;

import com.plazas.usuarios.application.dto.OwnerResponse;
import com.plazas.usuarios.application.dto.RolResponse;
import com.plazas.usuarios.domain.model.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RolResponseMapper {

    @Mapping(source = "rol", target = "rol")
    RolResponse toResponse(Owner owner);
}
