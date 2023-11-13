export class SignupRequest{
    private stageName:String;
    private email: String;
    private password: String;

    public constructor(stageName:String, email:String, password:String){
        this.stageName = stageName;
        this.email = email;
        this.password = password;
    }

    public getStageName():String{
        return this.stageName;
    }
    public setStageName(stageName:String):void{
        this.stageName = stageName;
    }

    public getEmail():String{
        return this.email;
    }
    public setEmail(email:String):void{
        this.email = email;
    }

    public getPassword():String{
        return this.password;
    }
    public setPassword(password:String):void{
        this.password = password;
    }
}