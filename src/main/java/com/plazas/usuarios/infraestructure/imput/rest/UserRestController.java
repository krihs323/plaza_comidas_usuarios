package com.plazas.usuarios.infraestructure.imput.rest;

import com.plazas.usuarios.application.dto.*;
import com.plazas.usuarios.application.handler.IAuthenticationHandler;
import com.plazas.usuarios.application.handler.ICustomerHandler;
import com.plazas.usuarios.application.handler.IOwnerHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user/")
public class UserRestController {

    private final IOwnerHandler ownerHandler;
    private final ICustomerHandler customerHandler;
    private final IAuthenticationHandler authenticate;

    public UserRestController(IOwnerHandler ownerHandler, ICustomerHandler customerHandler, IAuthenticationHandler authenticate) {
        this.ownerHandler = ownerHandler;
        this.customerHandler = customerHandler;
        this.authenticate = authenticate;
    }

    @Operation(summary = "Get a rol by its User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OwnerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Owner not found", content = @Content)
    })
    @GetMapping("/rol/id/{id}")
    public ResponseEntity<RolResponse> getRolFromOwner(@Parameter(description = "Number of the user to be returned")
                                                                 @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(ownerHandler.getRolFromOwner(id));
    }

    @Operation(summary = "Get a rol by Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OwnerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Owner not found", content = @Content)
    })
    @GetMapping("/rol/email/{email}")
    public ResponseEntity<RolResponse> getRolFromEmail(@Parameter(description = "Number of the user to be returned")
                                                       @PathVariable(name = "email") String email) {
        return ResponseEntity.ok(ownerHandler.getRolFromEmail(email));
    }

    @Operation(summary = "Add a new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)
    })
    @PostMapping("/owner/")
    public ResponseEntity<?> saveOwner(@RequestBody @Valid OwnerRequest ownerRequest) {
        ownerHandler.saveOwner(ownerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Employee already exists", content = @Content)
    })
    @PostMapping("/employee/")
    public ResponseEntity<?> saveEmployee(@RequestBody @Valid OwnerRequest ownerRequest) {
        ownerHandler.saveEmployee(ownerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Add a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Customer already exists", content = @Content)
    })
    @PostMapping("/customer/")
    public ResponseEntity<?> saveCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        customerHandler.saveCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/authenticate/")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticate.authenticate(request));
    }

}
