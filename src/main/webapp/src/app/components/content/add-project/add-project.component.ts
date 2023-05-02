import { Component, OnInit, EventEmitter, Inject, Input, Output } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog'
import {Project} from "../../../entities/Project";
import {Task} from "../../../entities/Task";
@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {
  name : string = ""
  description : string = ""
  constructor(public dialogRef: MatDialogRef<AddProjectComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  create(){
    let project : Project = {
      id : -1,
      name: this.name,
      description : this.description,
      tasks : [],
      userId : -1,
      participants : [],
      requests : []
    }
    console.log(project)
    this.dialogRef.close(project);
  }



}
