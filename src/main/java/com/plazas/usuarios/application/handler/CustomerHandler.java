package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.CustomerRequest;
import com.plazas.usuarios.application.mapper.CustomerRequestMapper;
import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import org.springframework.stereotype.Service;

@Service
//@Transactional
public class CustomerHandler implements ICustomerHandler {

    private final IUserServicePort userServicePort;
//    private final RolResponseMapper rolResponseMapper;
    private final CustomerRequestMapper customerRequestMapper;

    public CustomerHandler(IUserServicePort userServicePort, CustomerRequestMapper customerRequestMapper) {
        this.userServicePort = userServicePort;
        this.customerRequestMapper = customerRequestMapper;
    }


    @Override
    public void saveCustomer(CustomerRequest customerRequest) {
        User user = customerRequestMapper.toOwner(customerRequest);
        user.setRole(Role.CUSTOMER);
        userServicePort.saveOwner(user);
    }
}
