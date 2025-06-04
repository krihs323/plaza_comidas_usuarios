package com.plazas.usuarios.domain.usercase;

import com.plazas.usuarios.domain.exception.ExceptionValidationsResponse;
import com.plazas.usuarios.domain.exception.UserCaseValidationException;
import com.plazas.usuarios.domain.model.Role;
import com.plazas.usuarios.domain.model.User;
import com.plazas.usuarios.domain.spi.IUserPersistencePort;
import com.plazas.usuarios.infraestructure.exception.UserAlreadyExistException;
import com.plazas.usuarios.infraestructure.exception.UserValidationException;
import com.plazas.usuarios.infraestructure.exceptionhandler.ExceptionResponse;
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


class UserUserCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;


    @InjectMocks
    private UserUserCase userUserCase;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        LocalDate birthDate = LocalDate.of(1989,3,23);
        user = new User("Cristian", "Botina", 123456L, "3155828235",
                birthDate, "cris@hotmail.com", "34567", Role.OWNER, 1L, null);
    }


    @Test
    @DisplayName("Save owner should save")
    void saveOwner() {
        doNothing().when(userPersistencePort).saveOwner(any());
        userUserCase.saveOwner(user);
        verify(userPersistencePort).saveOwner(user);
    }

    @Test
    @DisplayName("Save owner should fail with OwnerValidationException")
    void testErrorWhenIsNotAdult(){
        doNothing().when(userPersistencePort).saveOwner(any());

        LocalDate birthDate = LocalDate.of(2020,3,23);

        UserValidationException exception = assertThrows(UserValidationException.class, () -> {
            new User("Cristian", "Botina", 123456L, "3155828235",
                    birthDate, "cris@hotmail.com", "34567", Role.ADMIN, 1L, null);
        });
        assertEquals(ExceptionValidationsResponse.USER_VALIATION_AGE.getMessage(), exception.getMessage());

    }

    @Test
    @DisplayName("Get rol by owner")
    void getRolFromOwner() {

        Mockito.when(userPersistencePort.getRolFromOwner(anyLong())).thenReturn(user);
        User userExpected = userUserCase.getRolFromOwner(anyLong());
        assertEquals(userExpected.getEmail(), user.getEmail());

    }

    @Test
    @DisplayName("Get user by email")
    void findByEmail() {

        Mockito.when(userPersistencePort.findByEmail(anyString())).thenReturn(Optional.of(user));

        UserAlreadyExistException exception = assertThrows(UserAlreadyExistException.class, () ->
            userUserCase.findByEmail(anyString())
        );

        assertEquals(ExceptionResponse.USER_VALIDATION_EXIST.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Save employee should save")
    void saveEmployee() {
        doNothing().when(userPersistencePort).saveOwner(any());
        userUserCase.saveEmployee(user);
        verify(userPersistencePort).saveOwner(user);
    }

    @Test
    @DisplayName("Save employee should dont save")
    void saveEmployeeVWhenUserAlreadyExist() {
        Mockito.when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Mockito.when(userPersistencePort.encodePassword(user.getPassword())).thenReturn(anyString());

        UserAlreadyExistException exception = assertThrows(UserAlreadyExistException.class, () -> {
            userUserCase.saveEmployee(user);
        });
        assertEquals(ExceptionResponse.USER_VALIDATION_EXIST.getMessage() + user.getEmail(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid email in Employee")
    void saveEmployeeInvalidEmail() {

        user.setEmail("email.@");
        UserCaseValidationException exception = assertThrows(UserCaseValidationException.class, () ->
            userUserCase.saveEmployee(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIDATION_EMAIL.getMessage(), exception.getMessage());

        user.setEmail("");
        UserCaseValidationException exception2 = assertThrows(UserCaseValidationException.class, () ->
            userUserCase.saveEmployee(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIDATION_EMAIL.getMessage(), exception2.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid name in Employee")
    void saveEmployeeInvalidName() {

        user.setName("");
        UserCaseValidationException exception2 = assertThrows(UserCaseValidationException.class, () ->
                userUserCase.saveEmployee(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIATION_NAME.getMessage(), exception2.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid last name in Employee")
    void saveEmployeeInvalidLastName() {

        user.setLastName("");
        UserCaseValidationException exception = assertThrows(UserCaseValidationException.class, () ->
                userUserCase.saveEmployee(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIDATION_LAST_NAME.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid number id in Employee")
    void saveEmployeeInvalidNumberId() {

        user.setNumberId(null);
        UserCaseValidationException exception = assertThrows(UserCaseValidationException.class, () ->
                userUserCase.saveEmployee(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIDATION_NUMBER_ID.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid phone number in Employee when is greater than 13")
    void saveEmployeeInvalidTelephone() {

        user.setPhoneNumber("+57315584842343");
        UserCaseValidationException exception = assertThrows(UserCaseValidationException.class, () ->
                userUserCase.saveEmployee(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIDATION_TELEPHONE.getMessage(), exception.getMessage());
    }


    @Test
    @DisplayName("Should return error when is invalid email in User")
    void saveUserInvalidEmail() {

        user.setEmail("email.@");
        UserCaseValidationException exception = assertThrows(UserCaseValidationException.class, () ->
                userUserCase.saveOwner(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIDATION_EMAIL.getMessage(), exception.getMessage());

        user.setEmail("");
        UserCaseValidationException exception2 = assertThrows(UserCaseValidationException.class, () ->
                userUserCase.saveOwner(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIDATION_EMAIL.getMessage(), exception2.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid name in User")
    void saveUserInvalidName() {

        user.setName("");
        UserCaseValidationException exception2 = assertThrows(UserCaseValidationException.class, () ->
                userUserCase.saveOwner(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIATION_NAME.getMessage(), exception2.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid last name in User")
    void saveUserInvalidLastName() {

        user.setLastName("");
        UserCaseValidationException exception = assertThrows(UserCaseValidationException.class, () ->
                userUserCase.saveOwner(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIDATION_LAST_NAME.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid number id in User")
    void saveUserInvalidNumberId() {

        user.setNumberId(null);
        UserCaseValidationException exception = assertThrows(UserCaseValidationException.class, () ->
                userUserCase.saveOwner(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIDATION_NUMBER_ID.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid phone number in User when is greater than 13")
    void saveUserInvalidTelephone() {

        user.setPhoneNumber("+57315584842343");
        UserCaseValidationException exception = assertThrows(UserCaseValidationException.class, () ->
                userUserCase.saveOwner(user)
        );
        assertEquals(ExceptionValidationsResponse.USER_VALIDATION_TELEPHONE.getMessage(), exception.getMessage());
    }


}