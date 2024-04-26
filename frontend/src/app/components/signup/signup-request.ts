export class SignupRequest{
    private userName:String;
    private email: String;
    private password: String;

    public constructor(userName:String, email:String, password:String){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public getUserName():String{
        return this.userName;
    }
    public setUserName(userName:String):void{
        this.userName = userName;
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