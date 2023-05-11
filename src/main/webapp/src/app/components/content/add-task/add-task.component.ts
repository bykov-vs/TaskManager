import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Project} from "../../../entities/Project";
import {Task} from "../../../entities/Task";
import {User} from "../../../entities/User";

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {
  name : string = ""
  description : string = ""
  constructor(public dialogRef: MatDialogRef<AddTaskComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  create(){
    let task : Task = {
      name: this.name,
      description : this.description,
      status : "todo",
      projectId : -1,
      performerId : -1
    }
    console.log(task)
    this.dialogRef.close(task);
  }

}
