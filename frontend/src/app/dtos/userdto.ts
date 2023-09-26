export class UserDto {
    private id: Number;
    private  stageName: String;
    private phoneNumber: String;
    private role: String;
    private userSocials: any

    constructor(id:Number, stageName: String, phoneNumber: String, role: String, userSocials:any){
        this.id =  id;
        this.stageName = stageName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.userSocials = userSocials;
    }
}
