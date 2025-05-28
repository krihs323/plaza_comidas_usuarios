package com.plazas.usuarios.infraestructure.imput.rest;

import com.plazas.usuarios.application.dto.OwnerResponse;
import com.plazas.usuarios.application.dto.RolResponse;
import com.plazas.usuarios.application.handler.IOwnerHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user/")
public class UserRestController {

    private final IOwnerHandler ownerHandler;

    public UserRestController(IOwnerHandler ownerHandler) {
        this.ownerHandler = ownerHandler;
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




}
