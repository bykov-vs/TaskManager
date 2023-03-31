import { Component } from '@angular/core';
import {User} from "./entities/User";
import {AuthServiceService} from "./services/auth-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'webapp';
  user : User | undefined
  constructor(private authService : AuthServiceService) {
     this.authService.user.subscribe(user => this.user = user)
  }
}
