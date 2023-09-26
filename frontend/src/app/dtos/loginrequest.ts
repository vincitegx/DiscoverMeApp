export class LoginRequest {
    public phoneNumber: String;
    public password: String;

    constructor(phoneNumber: string, password: string){
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    // public setPhoneNumber(phoneNumber:String): void{
    //     this.phoneNumber  = phoneNumber;
    // }

    // public getPhoneNumber():String{
    //     return this.phoneNumber;
    // }

    // public setPassword(password:String): void{
    //     this.password  = password;
    // }

    // public getPassword():String{
    //     return this.password;
    // }
}
