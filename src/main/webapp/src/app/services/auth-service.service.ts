import { Injectable } from '@angular/core';
import {User} from "../entities/User";
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, Subject, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  user = new BehaviorSubject<User | undefined>(undefined)
  isLoggedIn : boolean = false
  constructor(private http: HttpClient ) { }

  login(user : User) : Observable<User>{
    return this.http.post<User>('http://localhost:8080/login/', user)
      .pipe(tap(user => this.user.next(user)));
  }

  logout() {
    this.user.next(undefined)
  }

}
