import {Task} from "./Task";

export interface Project {
  id : number,
  name : string,
  description : string,
  tasks : Array<Task>,
  userId : number
}
