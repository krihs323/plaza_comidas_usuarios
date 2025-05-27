package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IOwnerPersistencePort;
import com.plazas.usuarios.infraestructure.exception.UserValidationException;
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


class UserUserCaseTest {

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

    private static User getUser() {
        LocalDate birthDate = LocalDate.of(1989,3,23);
        User userMock = new User("Cristian", "Botina", 123456L, "3155828235",
                birthDate, "cris@hotmail.com", "34567", Role.OWNER);
        return userMock;
    }

    @Test
    @DisplayName("Save owner should save")
    void saveOwner() {
        doNothing().when(ownerPersistencePort).saveOwner(any());
        //Mockito.when(passwordEncoder.encode(any())).thenReturn(anyString());

        User user = getUser();

        ownerUserCase.saveOwner(user);
        verify(ownerPersistencePort).saveOwner(user);

    }

    @Test
    @DisplayName("Save owner should fail with OwnerValidationException")
    void testErrorWhenIsNotAdult(){
        doNothing().when(ownerPersistencePort).saveOwner(any());
        //Mockito.when(passwordEncoder.encode(any())).thenReturn(any());

        LocalDate birthDate = LocalDate.of(2020,3,23);

        UserValidationException exception = assertThrows(UserValidationException.class, () -> {
            User user = new User("Cristian", "Botina", 123456L, "3155828235",
                    birthDate, "cris@hotmail.com", "34567", Role.ADMIN);
            //ownerUserCase.saveOwner(owner);
        });
        assertEquals("El usuario no debe ser menor de edad", exception.getMessage());

    }

    @Test
    @DisplayName("Get rol by owner")
    void getRolFromOwner() {

        User userMock = getUser();

        Mockito.when(ownerPersistencePort.getRolFromOwner(anyLong())).thenReturn(userMock);

        User userExpected = ownerUserCase.getRolFromOwner(anyLong());
        assertEquals(userExpected.getEmail(), userMock.getEmail());

    }



    @Test
    @DisplayName("Get user by email")
    void findByEmail() {

        User userMock = getUser();

        Mockito.when(ownerPersistencePort.findByEmail(anyString())).thenReturn(userMock);

        User userExpected = ownerUserCase.findByEmail(anyString());
        assertEquals(userExpected.getEmail(), userMock.getEmail());
    }




}