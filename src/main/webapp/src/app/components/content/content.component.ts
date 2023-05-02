import {Component, Input, OnInit} from '@angular/core';
import {Project} from "../../entities/Project";
import {Task} from "../../entities/Task";
import {AddProjectComponent} from "./add-project/add-project.component";
import {MatDialog} from "@angular/material/dialog";
import {AddTaskComponent} from "./add-task/add-task.component";
import {CdkDragDrop, DragDropModule, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {Router} from "@angular/router";
import {AuthServiceService} from "../../services/auth-service.service";
import {User} from "../../entities/User";
import {HttpService} from "../../services/HttpService";
import {ShowTaskComponent} from "../show-task/show-task.component";

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {
  projects : Project[] = []
  currentProject : Project | undefined
  tasks : Array<Task> = []
  todoTasks : Array<Task> = [];
  inProgressTasks : Array<Task> = [];
  doneTasks : Array<Task> = [];

  participants : Array<User> = []

  user : User | undefined

  isAuthenticated : boolean = false

  constructor(public dialog: MatDialog, private router: Router,
              private authService : AuthServiceService, private httpService : HttpService) {
    this.authService.user.subscribe(user => {
      this.user = user
      console.log(this.user)
      if (user && this.user !== undefined){
        this.isAuthenticated = true
        this.httpService.getProjects(user.id)
          .subscribe((data : any) => this.projects = data )
      }
    })
  }

  chooseProject(project : Project){
    this.currentProject = project
    console.log(this.currentProject)
    this.parseTasks()
    this.participants = this.currentProject.participants.slice()
  }
  parseTasks(){
    this.todoTasks = []
    this.inProgressTasks = []
    this.doneTasks = []
    if (this.currentProject === undefined){
      return
    }
    for (let task of this.currentProject.tasks){
      if (task.status === "todo"){
        this.todoTasks.push(task)
      }else if (task.status === "inProgress"){
        this.inProgressTasks.push(task)
      }else{
        this.doneTasks.push(task)
      }
    }
  }

  createProject(){
    const dialogRef = this.dialog.open(AddProjectComponent, {
      data: null,
    });

    dialogRef.afterClosed().subscribe(project => {
      if (project) {
        project.userId = this.user?.id
        this.httpService.saveProject(project)
          .subscribe(() => {
            if (this.user !== undefined){
              this.httpService.getProjects(this.user.id)
                .subscribe((data : any) => {
                  console.log(data)
                  this.projects = data
                })
            }
          })
      }
    })

  }

  createTask(){
    const dialogRef = this.dialog.open(AddTaskComponent, {
      data: null,
    });

    dialogRef.afterClosed().subscribe( task => {
      if (task) {
        console.log(this.currentProject)
        task.projectId = this.currentProject?.id

        this.parseTasks()
        this.httpService.saveTask(task)
          .subscribe(() => {
            this.httpService.getTasks(task.projectId)
              .subscribe((data : any) => {
                if (this.currentProject !== undefined){
                  this.currentProject.tasks = data
                  this.parseTasks()
                }
              })
          })
      }
    });
  }
  drop(event: CdkDragDrop<Array<Task>>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      )
      let tasks = []
      for (let task of this.todoTasks){
        task.status = "todo"
        tasks.push(task)
      }
      for (let task of this.inProgressTasks){
        task.status = "inProgress"
        tasks.push(task)
      }
      for (let task of this.doneTasks){
        task.status = "done"
        tasks.push(task)
      }
      console.log(tasks)
      this.httpService.saveTasks(tasks)
        .subscribe(() => {
          if (this.currentProject !== undefined){
            this.httpService.getTasks(this.currentProject.id)
              .subscribe((data: any) => {
                if (this.currentProject !== undefined){
                  this.currentProject.tasks = data
                  this.parseTasks()
                }
              })
          }
        })
    }
  }

  goToRegistration(){
    this.router.navigate(['/registration'])
  }
  goToLogin(){
    this.router.navigate(['/login'])
  }

  showTask(task: Task) {
    const dialogRef = this.dialog.open(ShowTaskComponent, {
      data: task,
    });

    dialogRef.afterClosed().subscribe( );
  }

  accept(event: any){
    let userId = event.target.id
    if (this.currentProject === undefined){
      return
    }
    let projectId = this.currentProject.id
    this.httpService.acceptParticipant(projectId, userId).subscribe(
      () => alert("Успешно!")
    )
  }

  reject(event: any){
    let userId = event.target.id
    if (this.currentProject === undefined){
      return
    }
    let projectId = this.currentProject.id
    this.httpService.rejectParticipant(projectId, userId).subscribe(
      () => alert("Успешно!")
    )
  }
}
