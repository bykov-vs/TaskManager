import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ContentComponent } from './components/content/content.component';
import { ProjectPanelComponent } from './components/content/project-panel/project-panel.component';
import { AddProjectComponent } from './components/content/add-project/add-project.component';
import {FormsModule} from "@angular/forms";
import { MatDialogModule } from '@angular/material/dialog';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { AddTaskComponent } from './components/content/add-task/add-task.component';
import {DragDropModule} from "@angular/cdk/drag-drop";
import {RouterModule, Routes} from "@angular/router";
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import {HttpService} from "./services/HttpService";
import {HttpClientModule} from "@angular/common/http";
import { ShowTaskComponent } from './components/show-task/show-task.component';
import { ProjectslistComponent } from './components/content/projectslist/projectslist.component';

const appRoutes: Routes =[
  { path: '', component: HomeComponent},
  { path: 'projects', component: ContentComponent},
  {path: 'registration', component:RegistrationComponent},
  {path: 'login', component:LoginComponent},
  {path: 'projects-list', component: ProjectslistComponent}
];
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ContentComponent,
    ProjectPanelComponent,
    AddProjectComponent,
    AddTaskComponent,
    HomeComponent,
    RegistrationComponent,
    LoginComponent,
    ShowTaskComponent,
    ProjectslistComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MatDialogModule,
    NoopAnimationsModule,
    DragDropModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
