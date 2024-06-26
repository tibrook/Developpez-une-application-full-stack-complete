import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

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
