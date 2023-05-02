import {Task} from "./Task";
import {User} from "./User";

export interface Project {
  id : number,
  name : string,
  description : string,
  tasks : Array<Task>,
  userId : number,
  participants : Array<User>,
  requests : Array<User>
}
