package com.openclassrooms.mddapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//https://www.baeldung.com/registration-password-strength-and-rules

/**
 * Annotation for validating password strength.
 * The password must meet the following criteria:
 * - At least 8 characters long
 * - Contains at least one digit
 * - Contains at least one lowercase letter
 * - Contains at least one uppercase letter
 * - Contains at least one special character
 */
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
	
	 /**
     * The default message to be used when the password validation fails.
     * 
     * @return the error message
     */
    String message() default "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character.";
    
    /**
     * Allows the specification of validation groups, to which this constraint belongs.
     * This must default to an empty array of type Class.
     * 
     * @return the array of classes
     */
    Class<?>[] groups() default {};
    
    /**
     * Can be used by clients of the Jakarta Bean Validation API to assign custom payload objects to a constraint.
     * This attribute is not used by the API itself.
     * 
     * @return the array of payload classes
     */
    Class<? extends Payload>[] payload() default {};
}