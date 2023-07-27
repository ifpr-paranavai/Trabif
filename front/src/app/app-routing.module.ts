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

const routes: Routes = [
  {path: 'modalBase', component: ModalBaseComponent},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'event', component: EventComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'eventAdd', component: EventAddComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'main', component: MainComponent, canActivate: [AuthenticatedUserGuard]},
  {path: 'register', component: RegisterComponent},
  {path: 'recoverPassword', component: RecoverPasswordComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
