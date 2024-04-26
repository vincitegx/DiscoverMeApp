import { Socials } from "./socials";

export class Project {
    id?: number;
    url?: string;
    songUri?:string;
    songTitle?:string;
    userName?:string;
    social?:Socials;
    contentUri?: string;
    percentOfSupport?:number;
    supported?:boolean;
}