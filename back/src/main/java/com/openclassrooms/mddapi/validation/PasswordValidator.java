package com.openclassrooms.mddapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//https://www.baeldung.com/registration-password-strength-and-rules
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    /**
     * Validates that the provided password meets the required strength criteria.
     * The password must be at least 8 characters long and contain at least:
     * - One digit
     * - One lowercase letter
     * - One uppercase letter
     * - One special character
     *
     * @param password the password to validate
     * @param context context in which the constraint is evaluated
     * @return true if the password is valid, false otherwise
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        return password.length() >= 8 && hasDigit && hasLowerCase && hasUpperCase && hasSpecialChar;
    }
}
