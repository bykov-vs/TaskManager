import {User} from "./User";

export interface Task {
  name : string,
  description : string,
  status : string,
  performerId : number,
  projectId : number
}
