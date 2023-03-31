import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HttpService} from "../../services/HttpService";
import {Router} from "@angular/router";
import {User} from "../../entities/User";
import {AuthServiceService} from "../../services/auth-service.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username : string = ""
  password : string = ""
  result : string = ""

  user : User | undefined

  constructor(private service : HttpService, private router : Router, private authService : AuthServiceService) { }

  ngOnInit(): void {
  }

  login(){
    const user : User = {
      id : -1,
      username : this.username,
      password : this.password,
      projects : []
    }
    console.log(user)
    this.authService.login(user)
      .subscribe(() => this.router.navigate(['']))

  }
}
