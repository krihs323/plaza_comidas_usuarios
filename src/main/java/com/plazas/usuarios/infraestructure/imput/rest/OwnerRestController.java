package com.plazas.usuarios.infraestructure.imput.rest;

import com.plazas.usuarios.application.dto.OwnerRequest;
import com.plazas.usuarios.application.dto.OwnerResponse;
import com.plazas.usuarios.application.dto.RolResponse;
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
@RequestMapping(path = "/api/user")
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

//    @Operation(summary = "Get a rol by its User")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Rol found",
//                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OwnerResponse.class))),
//            @ApiResponse(responseCode = "404", description = "Owner not found", content = @Content)
//    })
//    @GetMapping("/rol/{id}")
//    public ResponseEntity<RolResponse> getRolFromOwner(@Parameter(description = "Number of the user to be returned")
//                                                                 @PathVariable(name = "id") Long id) {
//        return ResponseEntity.ok(ownerHandler.getRolFromOwner(id));
//    }




}
