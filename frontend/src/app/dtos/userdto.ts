import { UserSocials } from "./usersocial";

export interface UserDto {
    id?: Number;
    userName?: String;
    email?: String;
    role?: String;
    socials?: UserSocials[];
}
