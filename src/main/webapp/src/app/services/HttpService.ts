import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from "../entities/User";
import {Project} from "../entities/Project";
import {Task} from "../entities/Task";

@Injectable()
export class HttpService {

  constructor(private http: HttpClient) {
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
}
