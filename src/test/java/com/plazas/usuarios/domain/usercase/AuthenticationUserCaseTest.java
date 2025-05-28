package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.application.dto.AuthenticationRequest;
import com.plazas.usuarios.domain.api.IUserServicePort;
import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IAuthenticationPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

class AuthenticationUserCaseTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private IUserServicePort ownerServicePort;
    @Mock
    private IAuthenticationPersistencePort authenticationPersistencePort;

    @InjectMocks
    AuthenticationUserCase authenticationUserCase;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Authenticate should return a string token")
    void authenticate() {

        LocalDate birthDate = LocalDate.of(1989,3,23);
        User userMock = new User("Cristian", "Botina", 123456L, "3155828235",
                birthDate, "cris@hotmail.com", "34567", Role.OWNER, 1L);

        Mockito.when(ownerServicePort.findByEmail(anyString())).thenReturn(userMock);

        String token = "tokenGenerada";
        Mockito.when(authenticationPersistencePort.authenticate(any())).thenReturn(token);


        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("cris@hotmail.com");
        request.setEmail("123456");

        String tokenToTest = authenticationUserCase.authenticate(request);

    }
}