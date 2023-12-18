import { Socials } from "./socials";

export class Project {
    id?: number;
    songUri?:string;
    songTitle?:string;
    stageName?:string;
    social?:Socials;
    contentUri?: string;
    noOfSupportedProjects?:number;
    noOfVoters?:number;
    voted?:boolean;
    isSupported?:boolean;
}