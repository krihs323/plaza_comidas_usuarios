package com.plazas.usuarios.infraestructure.imput.rest;

import com.plazas.usuarios.application.dto.OwnerRequest;
import com.plazas.usuarios.application.handler.IOwnerHandler;
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
@RequestMapping(path = "/api/user/employee")
public class EmployeeRestController {

    private final IOwnerHandler ownerHandler;
    public EmployeeRestController(IOwnerHandler ownerHandler) {
        this.ownerHandler = ownerHandler;
    }

    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Employee already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<?> saveEmployee(@RequestBody @Valid OwnerRequest ownerRequest) {
        ownerHandler.saveEmployee(ownerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
