import { Component, OnInit } from '@angular/core';
import {AuthServiceService} from "../../services/auth-service.service";
import {User} from "../../entities/User";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isAuthenticated : boolean = false
  user : User | undefined
  constructor(private authService : AuthServiceService) {
    this.authService.user.subscribe(user => {
      this.user = user
      if (this.user){
        this.isAuthenticated = true
      }
    })
  }

  logout(){
    this.authService.logout()
    this.user = undefined
    this.isAuthenticated = false
    console.log("logout")
  }

}
