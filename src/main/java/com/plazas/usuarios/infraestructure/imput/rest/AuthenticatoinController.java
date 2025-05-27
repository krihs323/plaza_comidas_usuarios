package com.plazas.usuarios.infraestructure.imput.rest;

import com.plazas.usuarios.application.dto.AuthenticationRequest;
import com.plazas.usuarios.application.dto.AuthenticationResponse;
import com.plazas.usuarios.application.handler.IAuthenticationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticatoinController {

    private final IAuthenticationHandler authenticate;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticate.authenticate(request));
    }
}
