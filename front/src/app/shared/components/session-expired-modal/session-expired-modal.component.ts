import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

/**
 * Component that represents a modal dialog for session expiration notifications.
 * This modal provides feedback to the user that their session has expired and offers
 * a mechanism to close the modal, typically leading to a logout or re-authentication flow.
 *
 * @Component Decorator that specifies the Angular metadata for the component.
 */
@Component({
  selector: 'app-session-expired-modal',
  templateUrl: './session-expired-modal.component.html',
  styleUrls: ['./session-expired-modal.component.scss']
})
export class SessionExpiredModalComponent {
  constructor(public dialogRef: MatDialogRef<SessionExpiredModalComponent>) {}
/**
   * Closes the dialog. This method can be tied to a button or other UI elements
   * within the dialog to allow users to manually close the dialog.
   */
  close(): void {
    this.dialogRef.close();
  }
}
