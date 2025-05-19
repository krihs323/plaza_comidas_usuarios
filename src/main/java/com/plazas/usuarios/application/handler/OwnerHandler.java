package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.OwnerRequest;
import com.plazas.usuarios.application.mapper.OwnerRequestMapper;
import com.plazas.usuarios.application.mapper.OwnerResponseMapper;
import com.plazas.usuarios.domain.api.IOwnerServicePort;
import com.plazas.usuarios.domain.model.Owner;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
//@Transactional
public class OwnerHandler implements IOwnerHandler {

    //@Autowired
    private final IOwnerServicePort ownerServicePort;
    private final OwnerResponseMapper ownerResponseMapper;
    private final OwnerRequestMapper ownerRequestMapper;

    public OwnerHandler(IOwnerServicePort ownerServicePort, OwnerResponseMapper ownerResponseMapper, OwnerRequestMapper ownerRequestMapper) {
        this.ownerServicePort = ownerServicePort;
        this.ownerResponseMapper = ownerResponseMapper;
        this.ownerRequestMapper = ownerRequestMapper;
    }

    @Override
    public void saveOwner(OwnerRequest ownerRequest) {
        //Implementa los casos de uso que se comunican con Dominio y Persistencia a traves de puertos
        Owner owner = ownerRequestMapper.toOwner(ownerRequest);
        ownerServicePort.saveOwner(owner);

    }

}
