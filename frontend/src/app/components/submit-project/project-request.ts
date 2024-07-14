import { Socials } from "src/app/dtos/socials";

export class ProjectRequest {
    public title: String;
    private social: Socials;

    public constructor(title: string, social: Socials){
        this.title = title;
        this.social = social;
    }

    public getTitle():String{
        return this.title;
    }
    public setTitle(title:String):void{
        this.title = title;
    }

    public getSocial():Socials{
        return this.social;
    }
    public setSocial(social:Socials):void{
        this.social = social;
    }
}
