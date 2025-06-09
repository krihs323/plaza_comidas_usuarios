package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.CustomerRequest;
import com.plazas.usuarios.application.mapper.CustomerRequestMapper;
import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerHandler implements ICustomerHandler {

    private final IUserServicePort userServicePort;
    private final CustomerRequestMapper customerRequestMapper;

    public CustomerHandler(IUserServicePort userServicePort, CustomerRequestMapper customerRequestMapper) {
        this.userServicePort = userServicePort;
        this.customerRequestMapper = customerRequestMapper;
    }

    @Override
    public void saveCustomer(CustomerRequest customerRequest) {
        User user = customerRequestMapper.toUser(customerRequest);
        //TODO Se maneja en el user Case - Ajustado
        userServicePort.saveCustomer(user);
    }
}
