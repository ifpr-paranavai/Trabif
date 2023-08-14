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
  {path: 'area', component: AreaComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'areaAdd', component: AreaAddComponent, canActivate: [AuthenticatedUserGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
