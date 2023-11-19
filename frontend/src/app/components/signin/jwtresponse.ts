import { UserDto } from "../../dtos/userdto";

export class JwtResponse {
    private authToken: string;
    private refreshToken: string;
    private user: UserDto;

    constructor(authToken: string, refreshToken: string, user: UserDto) {
        this.authToken = authToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public getAuthToken():string{
        return this.authToken;
    }
    public setAuthToken(authToken:string):void{
        this.authToken = authToken;
    }

    public getRefreshToken():string{
        return this.refreshToken;
    }
    public setRefreshToken(refreshToken:string):void{
        this.refreshToken = refreshToken;
    }

    public getUser():UserDto{
        return this.user;
    }
    public setUser(user: UserDto):void{
        this.user = user;
    }

}
