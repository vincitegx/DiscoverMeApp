import { Socials } from "./socials";

export class Project {
    id?: number;
    url?: string;
    songUri?:string;
    songTitle?:string;
    stageName?:string;
    social?:Socials;
    contentUri?: string;
    percentOfSupport?:number;
    isSupported?:boolean;
}