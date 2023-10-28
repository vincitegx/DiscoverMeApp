export class LoginRequest {
    public phoneNumber: String;
    public password: String;

    constructor(phoneNumber: string, password: string){
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
