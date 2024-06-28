import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackButtonComponent } from './back-button/back-button.component';
import { LoaderComponent } from './loader/loader.component';
import { SessionExpiredModalComponent } from './session-expired-modal/session-expired-modal.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatDialogModule } from '@angular/material/dialog';
@NgModule({
  declarations: [
    BackButtonComponent,
    LoaderComponent,
    SessionExpiredModalComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule,
  ],
  exports: [
    BackButtonComponent,
    LoaderComponent,
    SessionExpiredModalComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],

})
export class SharedModule { }
