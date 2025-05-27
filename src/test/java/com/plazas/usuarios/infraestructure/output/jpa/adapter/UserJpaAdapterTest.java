package com.plazas.usuarios.infraestructure.output.jpa.adapter;

import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.infraestructure.exception.UserAlreadyExistException;
import com.plazas.usuarios.infraestructure.exception.UserDoesNotExist;
import com.plazas.usuarios.infraestructure.exceptionhandler.ExceptionResponse;
import com.plazas.usuarios.infraestructure.output.jpa.entity.OwnerEntity;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.OwnerEntityMapper;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IOwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    @Mock
    private IOwnerRepository ownerRepository;

    @Mock
    private OwnerEntityMapper ownerEntityMapper;

    @InjectMocks
    private OwnerJpaAdapter ownerJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static User getUser() {
        LocalDate birthDate = LocalDate.of(1989,3,23);

        User user = new User();
        user.setName("Cristian");
        user.setLastName("Botina");
        user.setNumberId(123456L);
        user.setPhoneNumber("3155828235");
        user.setBirthDate(birthDate);
        user.setEmail("cris@hotmail.com");
        user.setPassword("34567");
        user.setRole(Role.OWNER);

        return user;

    }

    private static OwnerEntity getOwnerEntity() {
        LocalDate birthDate = LocalDate.of(1989,3,23);
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setId(1L);
        ownerEntity.setEmail("cris@hotmail.com");
        ownerEntity.setName("cristian");
        ownerEntity.setLastName("botina");
        ownerEntity.setPassword("123456");
        ownerEntity.setRole(Role.ADMIN);
        ownerEntity.setBirthDate(birthDate);
        ownerEntity.setPhoneNumber("+523155465");
        return ownerEntity;
    }

    @Test
    @DisplayName("Save owner should fail with OwnerAlreadyExistException")
    void saveOwnerAlreadyExist() {

        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setName("Cristian");
        ownerEntity.setLastName("Botina");
        ownerEntity.setEmail("cristian@hotmail.com");
        ownerEntity.setId(1L);

        Mockito.when(ownerRepository.findByEmail("cristian@hotmail.com")).thenReturn(Optional.of(ownerEntity));

        OwnerEntity savedJpaEntity = new OwnerEntity();
        savedJpaEntity.setId(1L);
        savedJpaEntity.setEmail("cristian@hotmail.com");
        savedJpaEntity.setName("Cristian");

        when(ownerRepository.save(any())).thenReturn(any());

        LocalDate birthDate = LocalDate.of(1989,3,23);
        User user = new User("Cristian", "Botina", 123456L, "3155828235",
                birthDate, "cristian@hotmail.com", "34567", Role.OWNER);

        UserAlreadyExistException exception = assertThrows(UserAlreadyExistException.class, () -> {
            ownerJpaAdapter.saveOwner(user);
        });


        verify(ownerRepository, never()).save(savedJpaEntity);
        String messageValidation = ExceptionResponse.USER_VALIDATION_EXIST.getMessage();
        assertTrue(exception.getMessage().contains(messageValidation));
    }


    @Test
    @DisplayName("Get rol from Owner")
    void getRolFromOwner(){

        OwnerEntity ownerEntity = getOwnerEntity();

        Optional<OwnerEntity> ownerMock = Optional.of(ownerEntity);

        Mockito.when(ownerRepository.findById(anyLong())).thenReturn(ownerMock);

        User userMock = getUser();
        Mockito.when(ownerEntityMapper.toOwner(any())).thenReturn(userMock);

        User userExpected = ownerJpaAdapter.getRolFromOwner(anyLong());

        assertEquals(userExpected.getEmail(), userMock.getEmail());


    }

    @Test
    @DisplayName("Get rol from Owner return exception not exist")
    void getRolFromOwnerException(){

        Mockito.when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        UserDoesNotExist exception = assertThrows(UserDoesNotExist.class, () ->
            ownerJpaAdapter.getRolFromOwner(anyLong())
        );

        String messageValidation = ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage();
        assertTrue(exception.getMessage().contains(messageValidation));

    }


    @Test
    @DisplayName("Get rol by email")
    void findByEmail(){
        OwnerEntity ownerEntity = getOwnerEntity();

        Optional<OwnerEntity> ownerMock = Optional.of(ownerEntity);

        Mockito.when(ownerRepository.findByEmail(anyString())).thenReturn(ownerMock);

        User userMock = getUser();
        Mockito.when(ownerEntityMapper.toOwner(any())).thenReturn(userMock);

        User userExpected = ownerJpaAdapter.findByEmail(anyString());

        assertEquals(userExpected.getEmail(), userMock.getEmail());
    }

    @Test
    @DisplayName("Get user by id returns exception not exist")
    void findByEmailUserNotExistException(){

        Mockito.when(ownerRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserDoesNotExist exception = assertThrows(UserDoesNotExist.class, () ->
                ownerJpaAdapter.findByEmail(anyString())
        );

        String messageValidation = ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage();
        assertTrue(exception.getMessage().contains(messageValidation));

    }

}