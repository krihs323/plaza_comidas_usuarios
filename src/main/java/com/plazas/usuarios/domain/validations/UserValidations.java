package com.plazas.usuarios.domain.validations;

import com.plazas.usuarios.domain.exception.ExceptionValidationsResponse;
import com.plazas.usuarios.domain.exception.UserCaseValidationException;
import com.plazas.usuarios.domain.model.User;

public class UserValidations {

    public static void saveUser(User user){

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new UserCaseValidationException(ExceptionValidationsResponse.USER_VALIATION_NAME.getMessage());
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new UserCaseValidationException(ExceptionValidationsResponse.USER_VALIDATION_LAST_NAME.getMessage());
        }

        if (!validate(user.getNumberId(), ConstantValidation.PATTERN_NUMBER.getValue())) {
            throw new UserCaseValidationException(ExceptionValidationsResponse.USER_VALIDATION_NUMBER_ID.getMessage());
        }

        if (!validate(user.getPhoneNumber(), ConstantValidation.PATTERN_TELEPHONE.getValue())) {
            throw new UserCaseValidationException(ExceptionValidationsResponse.USER_VALIDATION_TELEPHONE.getMessage());
        }

        if (!validate(user.getEmail(), ConstantValidation.PATTERN_EMAIL.getValue())) {
            throw new UserCaseValidationException(ExceptionValidationsResponse.USER_VALIDATION_EMAIL.getMessage());
        }

    }

    public static void saveEmployee(User user) {

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new UserCaseValidationException(ExceptionValidationsResponse.USER_VALIATION_NAME.getMessage());
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new UserCaseValidationException(ExceptionValidationsResponse.USER_VALIDATION_LAST_NAME.getMessage());
        }

        if (!validate(user.getNumberId(), ConstantValidation.PATTERN_NUMBER.getValue())) {
            throw new UserCaseValidationException(ExceptionValidationsResponse.USER_VALIDATION_NUMBER_ID.getMessage());
        }

        if (!validate(user.getPhoneNumber(), ConstantValidation.PATTERN_TELEPHONE.getValue())) {
            throw new UserCaseValidationException(ExceptionValidationsResponse.USER_VALIDATION_TELEPHONE.getMessage());
        }

        if (!validate(user.getEmail(), ConstantValidation.PATTERN_EMAIL.getValue())) {
            throw new UserCaseValidationException(ExceptionValidationsResponse.USER_VALIDATION_EMAIL.getMessage());
        }

    }

    private static boolean validate(Object value, String pattern){
        String valueString = "";
        if (value == null) {
            return false;
        }

        if (value instanceof Long) {
            valueString = String.valueOf(value);
        } else if (value instanceof String) {
            valueString = ((String) value).trim();
        }

        return valueString.matches(pattern);
    }
}
