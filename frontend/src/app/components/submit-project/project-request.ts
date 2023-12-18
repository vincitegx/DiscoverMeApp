import { Socials } from "src/app/dtos/socials";

export class ProjectRequest {
    public songTitle: String;
    public songUri: String;
    private social: Socials;

    public constructor(songTitle: string, songUri: string, social: Socials){
        this.songTitle = songTitle;
        this.songUri = songUri;
        this.social = social;
    }

    public getSongTitle():String{
        return this.songTitle;
    }
    public setSongTitle(songTitle:String):void{
        this.songTitle = songTitle;
    }

    public getSongUri():String{
        return this.songUri;
    }
    public setSongUri(songUri:String):void{
        this.songUri = songUri;
    }

    public getSocial():Socials{
        return this.social;
    }
    public setSocial(social:Socials):void{
        this.social = social;
    }
}
