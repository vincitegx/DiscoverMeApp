export class ProfileRequest {
    public username: String;
    constructor(username: string) {
        this.username = username;
    }

    public getUsername():String{
        return this.username;
    }
    public setUsername(username:String):void{
        this.username = username;
    }
}