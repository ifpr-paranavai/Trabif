import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ModalBaseComponent } from './components/modal-base/modal-base.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { EventComponent } from './pages/event/event.component';
import { MainComponent } from './pages/main/main.component';
import { HttpClientModule } from '@angular/common/http';
import { EventAddComponent } from './pages/event-add/event-add.component';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './pages/register/register.component';
import { RecoverPasswordComponent } from './pages/recover-password/recover-password.component';
import { EvaluatorComponent } from './pages/evaluator/evaluator.component';
import { EvaluatorAddComponent } from './pages/evaluator-add/evaluator-add.component';

@NgModule({
  declarations: [
    AppComponent,
    ModalBaseComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    EventComponent,
    MainComponent,
    EventAddComponent,
    RegisterComponent,
    RecoverPasswordComponent,
    EvaluatorComponent,
    EvaluatorAddComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
