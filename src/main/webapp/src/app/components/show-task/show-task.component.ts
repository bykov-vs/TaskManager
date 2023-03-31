import {Component, Inject, OnInit} from '@angular/core';
import {HttpService} from "../../services/HttpService";
import {Router} from "@angular/router";
import {User} from "../../entities/User";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Task} from "../../entities/Task";

@Component({
  selector: 'app-show-task',
  templateUrl: './show-task.component.html',
  styleUrls: ['./show-task.component.css']
})
export class ShowTaskComponent implements OnInit {

  name : string = ""
  description : string = ""

  constructor(public dialogRef: MatDialogRef<ShowTaskComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {

  }
  close(){
    this.dialogRef.close();
  }

}
