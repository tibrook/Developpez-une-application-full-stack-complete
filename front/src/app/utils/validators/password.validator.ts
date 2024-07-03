import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

/**
 * Creates a validator function to check the strength of a password.
 * Validates if the password contains:
 * - At least one uppercase letter
 * - At least one lowercase letter
 * - At least one number
 * - At least one special character
 * - Minimum length of 8 characters
 *
 * @returns A validator function suitable for use with Angular's form controls.
 */
export function passwordStrengthValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;
    if (!value) {
      return null;
    }

    const hasUpperCase = /[A-Z]+/.test(value);
    const hasLowerCase = /[a-z]+/.test(value);
    const hasNumeric = /[0-9]+/.test(value);
    const hasSpecial = /[!@#$%^&*(),.?":{}|<>]+/.test(value);
    const isValidLength = value.length >= 8;

    const passwordValid = hasUpperCase && hasLowerCase && hasNumeric && hasSpecial && isValidLength;

    return !passwordValid ? { passwordStrength: true } : null;
  };
}
