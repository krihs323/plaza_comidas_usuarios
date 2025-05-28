package com.plazas.usuarios.application.handler;

import com.plazas.usuarios.application.dto.CustomerRequest;

public interface ICustomerHandler {
    void saveCustomer(CustomerRequest customerRequest);
}
