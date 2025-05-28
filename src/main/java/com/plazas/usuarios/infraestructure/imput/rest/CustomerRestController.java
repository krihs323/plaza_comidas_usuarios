package com.plazas.usuarios.infraestructure.imput.rest;

import com.plazas.usuarios.application.dto.CustomerRequest;
import com.plazas.usuarios.application.handler.ICustomerHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/customer")
public class CustomerRestController {

    private final ICustomerHandler customerHandler;

    public CustomerRestController(ICustomerHandler customerHandler) {
        this.customerHandler = customerHandler;
    }

    @Operation(summary = "Add a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Customer already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<?> saveCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        customerHandler.saveCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
