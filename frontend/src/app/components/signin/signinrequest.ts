export class SigninRequest {
    public email: String;
    public password: String;

    public constructor(email: string, password: string){
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
