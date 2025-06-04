package com.plazas.usuarios.infraestructure.output.jpa.adapter;

import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
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
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    private User user;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        LocalDate birthDate = LocalDate.of(1989,3,23);

        user = new User();
        user.setName("Cristian");
        user.setLastName("Botina");
        user.setNumberId(123456L);
        user.setPhoneNumber("3155828235");
        user.setBirthDate(birthDate);
        user.setEmail("cris@hotmail.com");
        user.setPassword("34567");
        user.setRole(Role.OWNER);

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("cris@hotmail.com");
        userEntity.setName("cristian");
        userEntity.setLastName("botina");
        userEntity.setPassword("123456");
        userEntity.setRole(Role.ADMIN);
        userEntity.setBirthDate(birthDate);
        userEntity.setPhoneNumber("+523155465");
    }

    @Test
    @DisplayName("Save owner")
    void saveOwner() {
        when(ownerRepository.save(any())).thenReturn(any());

        userJpaAdapter.saveOwner(user);

        verify(ownerRepository, never()).save(userEntity);

    }

    @Test
    @DisplayName("Get rol from Owner")
    void getRolFromOwner(){

        Optional<UserEntity> ownerMock = Optional.of(userEntity);

        Mockito.when(ownerRepository.findById(anyLong())).thenReturn(ownerMock);
        Mockito.when(userEntityMapper.toOwner(any())).thenReturn(user);
        User userExpected = userJpaAdapter.getRolFromOwner(anyLong());

        assertEquals(userExpected.getEmail(), user.getEmail());
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

        Optional<UserEntity> ownerMock = Optional.of(userEntity);

        Mockito.when(ownerRepository.findByEmail(anyString())).thenReturn(ownerMock);

        Mockito.when(userEntityMapper.toOwner(any())).thenReturn(user);

        Optional<User> userExpected = userJpaAdapter.findByEmail(anyString());

        assertEquals(userExpected.get().getEmail(), user.getEmail());
    }

    @Test
    @DisplayName("Get user by id returns exception not exist")
    void findByEmailUserNotExistException(){

        String password = "encode";
        Mockito.when(passwordEncoder.encode(anyString())).thenReturn(password);

        String passwordResponse = userJpaAdapter.encodePassword(anyString());
        assertEquals(password, passwordResponse);
    }

    //@Test
    @DisplayName("Should encode Password")
    void encodePassword(){

        Mockito.when(ownerRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        userJpaAdapter.findByEmail(anyString());
    }

}