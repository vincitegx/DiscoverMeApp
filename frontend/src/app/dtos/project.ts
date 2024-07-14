import { Socials } from "./socials";

export class Project {
    id?: number;
    url?: string;
    title?:string;
    userName?:string;
    status?:string;
    social?:Socials;
    contentUri?: string;
    noOfSupport?:number;
    supported?:boolean;
    noOfReaction?:number;
    reacted?:boolean;
    publishDate?:string;
}