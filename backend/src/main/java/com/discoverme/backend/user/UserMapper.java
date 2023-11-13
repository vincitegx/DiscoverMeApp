package com.discoverme.backend.user;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper implements Function<Users, UserDto> {
    @Override
    public UserDto apply(Users user) {
        return UserDto.builder()
                .id(user.getId())
                .stageName(user.getStageName())
                .email(user.getEmail())
                .role(user.getRole())
//                .userSocials(mapUserSoocialsToDto(user.getUserSocials()))
                .build();
    }

//    private Set<UserSocialsDto> mapUserSoocialsToDto(Set<UserSocials> userSocials) {
//        Set<UserSocialsDto> userSocialsDtos = new HashSet<>();
//        userSocials.forEach(userSocial -> {
//            UserSocialsDto userSocialsDto = UserSocialsDto.builder()
//                    .uri(userSocial.getUri())
//                    .socialPlatform(getSocialPlatform(userSocial.getSocials().getName()))
//                    .id(userSocial.getId())
//                    .build();
//            userSocialsDtos.add(userSocialsDto);
//        });
//        return  userSocialsDtos;
//    }
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
