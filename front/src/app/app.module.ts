import { MessageService } from 'primeng/api';
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
import { ToastModule } from 'primeng/toast';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { MenubarModule } from 'primeng/menubar';
import { TabMenuModule } from 'primeng/tabmenu';
import { FinalizeRegistrationComponent } from './pages/finalize-registration/finalize-registration.component';
import { MyEventComponent } from './pages/my-event/my-event.component';
import { CategoryComponent } from './pages/category/category.component';
import { CategoryAddComponent } from './pages/category-add/category-add.component';
import { AreaComponent } from './pages/area/area.component';
import { AreaAddComponent } from './pages/area-add/area-add.component';


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
    FinalizeRegistrationComponent,
    MyEventComponent,
    CategoryComponent,
    CategoryAddComponent,
    AreaComponent,
    AreaAddComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ToastModule,
    ProgressSpinnerModule,
    TabMenuModule
  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
