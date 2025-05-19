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
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/user/owner")
//@RequiredArgsConstructor
public class OwnerRestController {

    private final IOwnerHandler ownerHandler;

    public OwnerRestController(IOwnerHandler ownerHandler) {
        this.ownerHandler = ownerHandler;
    }

    @Operation(summary = "Add a new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<?> saveOwner(@RequestBody @Valid OwnerRequest ownerRequest) {
        ownerHandler.saveOwner(ownerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
