import { UserDto } from "../../dtos/userdto";

export interface JwtResponse {
    authToken: string;
    user: UserDto;
}
