import { Component, OnInit } from '@angular/core';
import {HttpService} from "../../services/HttpService";
import {User} from "../../entities/User";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  username : string = ""
  password : string = ""
  result : string = ""



  constructor(private service : HttpService, private router : Router) { }

  ngOnInit(): void {
  }

  register(){
    const user : User = {
      id : -1,
      username : this.username,
      password : this.password,
      projects : []
    }

    this.service.register(user)
      .subscribe((data:any) => {
        this.result = data.status
        if (this.result === "successful"){
          alert("Регистрация прошла успешно!")
        }else {
          alert("Регистрация не прошла :(")
        }
        this.router.navigate([''])
      })

  }
}
