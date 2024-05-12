export class SignupRequest{
    private email: String;
    private password: String;

    public constructor(email:String, password:String){
        this.email = email;
        this.password = password;
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