package com.plazas.usuarios.application.mapper;

import com.plazas.usuarios.application.dto.OwnerResponse;
import com.plazas.usuarios.domain.model.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwnerResponseMapper {

    OwnerResponse toResponse(Owner pokemon);

}
