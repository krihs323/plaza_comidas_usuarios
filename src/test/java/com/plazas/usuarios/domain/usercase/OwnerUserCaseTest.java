package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.domain.model.Owner;
import com.plazas.usuarios.domain.spi.IOwnerPersistencePort;
import com.plazas.usuarios.infraestructure.exception.OwnerValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class OwnerUserCaseTest {

    @Mock
    private IOwnerPersistencePort ownerPersistencePort;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private OwnerUserCase ownerUserCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Save owner should save")
    void saveOwner() {
        doNothing().when(ownerPersistencePort).saveOwner(any());
        //Mockito.when(passwordEncoder.encode(any())).thenReturn(anyString());

        LocalDate birthDate = LocalDate.of(1989,3,23);
        Owner owner = new Owner("Cristian", "Botina", 123456L, "3155828235",
                birthDate, "cris@hotmail.com", "34567");

        ownerUserCase.saveOwner(owner);
        verify(ownerPersistencePort).saveOwner(owner);

    }

    @Test
    @DisplayName("Save owner should fail with OwnerValidationException")
    void testErrorWhenIsNotAdult(){
        doNothing().when(ownerPersistencePort).saveOwner(any());
        //Mockito.when(passwordEncoder.encode(any())).thenReturn(any());

        LocalDate birthDate = LocalDate.of(2020,3,23);

        OwnerValidationException exception = assertThrows(OwnerValidationException.class, () -> {
            Owner owner = new Owner("Cristian", "Botina", 123456L, "3155828235",
                    birthDate, "cris@hotmail.com", "34567");
            //ownerUserCase.saveOwner(owner);
        });
        assertEquals("El usuario no debe ser menor de edad", exception.getMessage());

    }
}