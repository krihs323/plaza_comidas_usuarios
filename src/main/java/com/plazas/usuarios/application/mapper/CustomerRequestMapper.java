package com.plazas.usuarios.application.mapper;

import com.plazas.usuarios.application.dto.CustomerRequest;
import com.plazas.usuarios.application.dto.OwnerRequest;
import com.plazas.usuarios.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CustomerRequestMapper {

    User toUser(CustomerRequest customerRequest);
}
