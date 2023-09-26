import { UserDto } from "./userdto";

export class JwtResponse {
    private authToken: String;
    private user: UserDto;

    constructor(authToken: String, user: UserDto){
        this.authToken =authToken;
        this.user = user;
    }

}
