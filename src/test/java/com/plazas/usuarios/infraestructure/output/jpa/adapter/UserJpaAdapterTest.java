package com.plazas.usuarios.infraestructure.output.jpa.adapter;

import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.infraestructure.exception.UserAlreadyExistException;
import com.plazas.usuarios.infraestructure.exception.UserDoesNotExist;
import com.plazas.usuarios.infraestructure.exceptionhandler.ExceptionResponse;
import com.plazas.usuarios.infraestructure.output.jpa.entity.UserEntity;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IUserRepository;
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
    private IUserRepository ownerRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

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

    private static UserEntity getOwnerEntity() {
        LocalDate birthDate = LocalDate.of(1989,3,23);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("cris@hotmail.com");
        userEntity.setName("cristian");
        userEntity.setLastName("botina");
        userEntity.setPassword("123456");
        userEntity.setRole(Role.ADMIN);
        userEntity.setBirthDate(birthDate);
        userEntity.setPhoneNumber("+523155465");
        return userEntity;
    }

    @Test
    @DisplayName("Save owner should fail with OwnerAlreadyExistException")
    void saveOwnerAlreadyExist() {

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Cristian");
        userEntity.setLastName("Botina");
        userEntity.setEmail("cristian@hotmail.com");
        userEntity.setId(1L);

        Mockito.when(ownerRepository.findByEmail("cristian@hotmail.com")).thenReturn(Optional.of(userEntity));

        UserEntity savedJpaEntity = new UserEntity();
        savedJpaEntity.setId(1L);
        savedJpaEntity.setEmail("cristian@hotmail.com");
        savedJpaEntity.setName("Cristian");

        when(ownerRepository.save(any())).thenReturn(any());

        LocalDate birthDate = LocalDate.of(1989,3,23);
        User user = new User("Cristian", "Botina", 123456L, "3155828235",
                birthDate, "cristian@hotmail.com", "34567", Role.OWNER, 1L);

        UserAlreadyExistException exception = assertThrows(UserAlreadyExistException.class, () -> {
            userJpaAdapter.saveOwner(user);
        });


        verify(ownerRepository, never()).save(savedJpaEntity);
        String messageValidation = ExceptionResponse.USER_VALIDATION_EXIST.getMessage();
        assertTrue(exception.getMessage().contains(messageValidation));
    }


    @Test
    @DisplayName("Get rol from Owner")
    void getRolFromOwner(){

        UserEntity userEntity = getOwnerEntity();

        Optional<UserEntity> ownerMock = Optional.of(userEntity);

        Mockito.when(ownerRepository.findById(anyLong())).thenReturn(ownerMock);

        User userMock = getUser();
        Mockito.when(userEntityMapper.toOwner(any())).thenReturn(userMock);

        User userExpected = userJpaAdapter.getRolFromOwner(anyLong());

        assertEquals(userExpected.getEmail(), userMock.getEmail());


    }

    @Test
    @DisplayName("Get rol from Owner return exception not exist")
    void getRolFromOwnerException(){

        Mockito.when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        UserDoesNotExist exception = assertThrows(UserDoesNotExist.class, () ->
            userJpaAdapter.getRolFromOwner(anyLong())
        );

        String messageValidation = ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage();
        assertTrue(exception.getMessage().contains(messageValidation));

    }


    @Test
    @DisplayName("Get rol by email")
    void findByEmail(){
        UserEntity userEntity = getOwnerEntity();

        Optional<UserEntity> ownerMock = Optional.of(userEntity);

        Mockito.when(ownerRepository.findByEmail(anyString())).thenReturn(ownerMock);

        User userMock = getUser();
        Mockito.when(userEntityMapper.toOwner(any())).thenReturn(userMock);

        User userExpected = userJpaAdapter.findByEmail(anyString());

        assertEquals(userExpected.getEmail(), userMock.getEmail());
    }

    @Test
    @DisplayName("Get user by id returns exception not exist")
    void findByEmailUserNotExistException(){

        Mockito.when(ownerRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserDoesNotExist exception = assertThrows(UserDoesNotExist.class, () ->
                userJpaAdapter.findByEmail(anyString())
        );

        String messageValidation = ExceptionResponse.USER_VALIDATION_NOT_FOUND.getMessage();
        assertTrue(exception.getMessage().contains(messageValidation));

    }

}