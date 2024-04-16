import { Routes } from '@angular/router';
import {RegisterComponent} from "./user/register.component";
import {TasksComponent} from "./tasks/tasks.component";

export const routes: Routes = [
  { path: '', component: TasksComponent },
  { path: 'home', component: TasksComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'tasks', component: TasksComponent },
];
