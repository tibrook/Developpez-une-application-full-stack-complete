import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

/**
 * Creates a validator function to check if a username is valid.
 * Validates if the username contains only alphanumeric characters.
 *
 * @returns A validator function suitable for use with Angular's form controls.
 */
export function usernameValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;
    if (!value) {
      return null;
    }

    // Allow only alphanumeric characters
    const isValid = /^[A-Za-z0-9]+$/.test(value);

    return !isValid ? { invalidUsername: true } : null;
  };
}
