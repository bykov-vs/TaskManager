import { Component, OnInit } from '@angular/core';
import {Project} from "../../../entities/Project";

@Component({
  selector: 'app-project-panel',
  templateUrl: './project-panel.component.html',
  styleUrls: ['./project-panel.component.css']
})
export class ProjectPanelComponent implements OnInit {
  projects : Project[] = []

  constructor() { }

  ngOnInit(): void {
  }

}
