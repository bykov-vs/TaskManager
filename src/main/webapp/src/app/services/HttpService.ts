import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from "../entities/User";
import {Project} from "../entities/Project";
import {Task} from "../entities/Task";
import * as http from "http";

@Injectable()
export class HttpService {

  constructor(private http: HttpClient) {
  }

  getProjectById(id : number){
    return this.http.get('http://localhost:8080/project/'+id);
  }

  getAllProjects(){
    return this.http.get('http://localhost:8080/project/global-all');
  }

  register(user : User) {
    return this.http.post('http://localhost:8080/registration/', user)
  }

  saveProject(project : Project){
    return this.http.post('http://localhost:8080/project/save', project)
  }

  saveTask(task : Task){
    return this.http.post('http://localhost:8080/task/save', task)
  }

  getProjects(id: number){
    return this.http.get('http://localhost:8080/project/all?id='+id)
  }

  saveTasks(tasks : Task[]){
    return this.http.post('http://localhost:8080/task/save-all', tasks)
  }

  getTasks(id : number){
    return this.http.get('http://localhost:8080/task/all?id='+id)
  }

  saveRequest(projectId : number, userId : number){
    let params = `projectId=${projectId}&userId=${userId}`
    return this.http.get('http://localhost:8080/project/saveRequest?'+params)
  }

  acceptParticipant(projectId : number, userId : number){
    let params = `projectId=${projectId}&userId=${userId}`
    return this.http.get('http://localhost:8080/project/acceptParticipant?'+params)
  }

  rejectParticipant(projectId : number, userId : number){
    let params = `projectId=${projectId}&userId=${userId}`
    return this.http.get('http://localhost:8080/project/rejectParticipant?'+params)
  }
}
