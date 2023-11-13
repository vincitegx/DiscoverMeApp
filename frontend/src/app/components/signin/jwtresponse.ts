import { UserDto } from "../../dtos/userdto";

export class JwtResponse {
    private authToken: String;
    private refreshToken: String;
    private user: UserDto;

    constructor(authToken: String, refreshToken: String, user: UserDto) {
        this.authToken = authToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public getAuthToken():String{
        return this.authToken;
    }
    public setAuthToken(authToken:String):void{
        this.authToken = authToken;
    }

    public getRefreshToken():String{
        return this.refreshToken;
    }
    public setRefreshToken(refreshToken:String):void{
        this.refreshToken = refreshToken;
    }

    public getUser():UserDto{
        return this.user;
    }
    public setUser(user: UserDto):void{
        this.user = user;
    }

}
