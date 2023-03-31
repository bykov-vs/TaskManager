import {Project} from "./Project";

export interface User {
  id : number,
  username : string,
  password : string,
  projects : Array<Project>
}
