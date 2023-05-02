import { Component, OnInit } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {AuthServiceService} from "../../../services/auth-service.service";
import {HttpService} from "../../../services/HttpService";
import {Project} from "../../../entities/Project";
import {data} from "autoprefixer";
import {User} from "../../../entities/User";

@Component({
  selector: 'app-projectslist',
  templateUrl: './projectslist.component.html',
  styleUrls: ['./projectslist.component.css']
})
export class ProjectslistComponent implements OnInit {
  projects : Project[] = []
  user : User | undefined
  infoBlock : any
  targetProject : Project | undefined

  constructor(private router: Router, private httpService : HttpService,
              private authService : AuthServiceService) {
    this.authService.user.subscribe(user => {
      this.user = user
    })
  }

  ngOnInit(): void {
    this.httpService.getAllProjects().subscribe((data : any) => this.projects = data)
  }

  getInfo(event : any){
    if (this.infoBlock != null){
      this.infoBlock.hidden = true
    }
    let id = event.target.id
    this.httpService.getProjectById(id).subscribe((data : any) => this.targetProject = data)
    this.infoBlock = document.getElementById("info"+id)
    console.log(this.targetProject)
    if (this.infoBlock != null){
      this.infoBlock.hidden = false
    }
  }

  sendRequest(event : any){
    let projectId = Number(event.target.name)
    if (this.user === undefined){
      alert("Чтобы присоединятся к проектам необходимо авторизоваться!")
    }else {
      let userId = this.user.id
      this.httpService.saveRequest(projectId, userId).subscribe(
        () => alert("Заявка отправлена!")
      )
    }

  }
}
