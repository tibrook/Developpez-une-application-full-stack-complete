import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-session-expired-modal',
  templateUrl: './session-expired-modal.component.html',
  styleUrls: ['./session-expired-modal.component.scss']
})
export class SessionExpiredModalComponent {
  constructor(public dialogRef: MatDialogRef<SessionExpiredModalComponent>) {}

  close(): void {
    this.dialogRef.close();
  }
}
