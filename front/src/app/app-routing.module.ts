import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ModalBaseComponent } from './components/modal-base/modal-base.component';
import { EventComponent } from './pages/event/event.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { MainComponent } from './pages/main/main.component';
import { EventAddComponent } from './pages/event-add/event-add.component';
import { RegisterComponent } from './pages/register/register.component';
import { RecoverPasswordComponent } from './pages/recover-password/recover-password.component';
import { AuthenticatedUserGuard } from './services/guards/authenticated-user.guard';
import { EvaluatorAddComponent } from './pages/evaluator-add/evaluator-add.component';
import { FinalizeRegistrationComponent } from './pages/finalize-registration/finalize-registration.component';
import { MyEventComponent } from './pages/my-event/my-event.component';
import { CategoryComponent } from './pages/category/category.component';
import { CategoryAddComponent } from './pages/category-add/category-add.component';
import { AreaComponent } from './pages/area/area.component';
import { AreaAddComponent } from './pages/area-add/area-add.component';
import { AreaEvaluatorComponent } from './pages/area-evaluator/area-evaluator.component';
import { AreaEvaluatorAddComponent } from './pages/area-evaluator-add/area-evaluator-add.component';
import { MyWorksComponent } from './pages/my-works/my-works.component';
import { WorkAddComponent } from './pages/work-add/work-add.component';
import { ChooseUserPermissionComponent } from './pages/choose-user-permission/choose-user-permission.component';
import { OrganizerAddComponent } from './pages/organizer-add/organizer-add.component';
import { EmailTemplateAddComponent } from './pages/email-template-add/email-template-add.component';
import { AreaUpdateComponent } from './pages/area-update/area-update.component';
import { CategoryUpdateComponent } from './pages/category-update/category-update.component';
import { EmailTemplateUpdateComponent } from './pages/email-template-update/email-template-update.component';
import { OrganizerWorkComponent } from './pages/organizer-work/organizer-work.component';
import { DefineEvaluatorComponent } from './pages/define-evaluator/define-evaluator.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'modalBase', component: ModalBaseComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'recoverPassword', component: RecoverPasswordComponent},
  {path: 'finalizar-cadastro/:id', component: FinalizeRegistrationComponent},
  {path: 'event', component: EventComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'eventAdd', component: EventAddComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'main', component: MainComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'evaluatorAdd', component: EvaluatorAddComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'myEvent', component: MyEventComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'categrory', component: CategoryComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'categoryAdd', component: CategoryAddComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'categoryUpdate', component: CategoryUpdateComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'area', component: AreaComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'areaAdd', component: AreaAddComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'areaUpdate', component: AreaUpdateComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'areaEvaluator', component: AreaEvaluatorComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'areaEvaluatorAdd', component: AreaEvaluatorAddComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'myWork', component: MyWorksComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'workAdd', component: WorkAddComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'chooseUserPermission', component: ChooseUserPermissionComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'organizerAdd', component: OrganizerAddComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'emailTemplateAdd', component: EmailTemplateAddComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'emailTemplateUpdate', component: EmailTemplateUpdateComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'organizerWork', component: OrganizerWorkComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'defineEvaluator', component: DefineEvaluatorComponent, canActivate: [AuthenticatedUserGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
