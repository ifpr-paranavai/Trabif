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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { AreaEvaluatorComponent } from './pages/area-evaluator/area-evaluator.component';
import { AreaEvaluatorAddComponent } from './pages/area-evaluator-add/area-evaluator-add.component';
import { MyWorksComponent } from './pages/my-works/my-works.component';
import { EventWorksComponent } from './pages/event-works/event-works.component';
import { WorkAddComponent } from './pages/work-add/work-add.component';
import { EvaluateWorkComponent } from './pages/evaluate-work/evaluate-work.component';
import { FileUploadModule } from 'primeng/fileupload';
import { ChooseUserPermissionComponent } from './pages/choose-user-permission/choose-user-permission.component';
import { OrganizerAddComponent } from './pages/organizer-add/organizer-add.component';
import { OrganizerComponent } from './pages/organizer/organizer.component';
import { EmailTemplateComponent } from './pages/email-template/email-template.component';
import { EmailTemplateAddComponent } from './pages/email-template-add/email-template-add.component';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { TooltipModule } from 'primeng/tooltip';
import { AreaUpdateComponent } from './pages/area-update/area-update.component';
import { CategoryUpdateComponent } from './pages/category-update/category-update.component';
import { EmailTemplateUpdateComponent } from './pages/email-template-update/email-template-update.component';
import { OrganizerWorkComponent } from './pages/organizer-work/organizer-work.component';
import { DefineEvaluatorComponent } from './pages/define-evaluator/define-evaluator.component';
import { EvaluateWorkListComponent } from './pages/evaluate-work-list/evaluate-work-list.component';


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
    AreaEvaluatorComponent,
    AreaEvaluatorAddComponent,
    MyWorksComponent,
    EventWorksComponent,
    WorkAddComponent,
    EvaluateWorkComponent,
    ChooseUserPermissionComponent,
    OrganizerAddComponent,
    OrganizerComponent,
    EmailTemplateComponent,
    EmailTemplateAddComponent,
    AreaUpdateComponent,
    CategoryUpdateComponent,
    EmailTemplateUpdateComponent,
    OrganizerWorkComponent,
    DefineEvaluatorComponent,
    EvaluateWorkListComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ToastModule,
    ProgressSpinnerModule,
    TabMenuModule,
    ReactiveFormsModule,
    FileUploadModule,
    InputTextareaModule,
    TooltipModule
  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
