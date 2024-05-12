package com.discoverme.backend.user;

import com.discoverme.backend.user.social.UserSocialsDto;
import com.discoverme.backend.user.social.UserSocialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserMapper implements Function<Users, UserDto> {

    private final UserSocialsService userSocialsService;
    @Override
    public UserDto apply(Users user) {
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(user.getRole())
                .socials(mapUserSocialsToDto(user))
                .build();
    }

    private List<UserSocialsDto> mapUserSocialsToDto(Users user) {
        return userSocialsService.findAllUserSocialsByUser(user);
    }
//
//    private SocialPlatform getSocialPlatform(String name) {
//        return switch (name) {
//            case "FACEBOOK" -> SocialPlatform.FACEBOOK;
//            case "X" -> SocialPlatform.X;
//            case "INSTAGRAM" -> SocialPlatform.INSTAGRAM;
//            case "TIKTOK" -> SocialPlatform.TIKTOK;
//            case "YOUTUBE" -> SocialPlatform.YOUTUBE;
//            default -> null;
//        };
//    }
}
