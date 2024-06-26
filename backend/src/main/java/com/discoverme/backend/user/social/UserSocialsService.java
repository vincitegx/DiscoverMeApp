package com.discoverme.backend.user.social;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.user.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserSocialsService {
    private final UserSocialsRepository userSocialsRepository;
//    private final UserService userService;
//    private final UserMapper userMapper;

//    public List<UserSocialsDto> findAllUserSocials() {
//        List<UserSocials> userSocials = userSocialsRepository.findByUser(userService.getCurrentUser());
//        return userSocials.stream().map(this::convertToDto)
//                .collect(Collectors.toList());
//    }

    public Set<UserSocialsDto> findAllUserSocialsByUser(Users user) {
        List<UserSocials> userSocials = userSocialsRepository.findByUser(user);
        return userSocials.stream().map(this::convertToDto)
                .collect(Collectors.toSet());
    }

    private UserSocialsDto convertToDto(UserSocials userSocials) {
//        UserDto userDto = convertUserToDto(userSocials.getUser());
        return UserSocialsDto.builder()
                .id(userSocials.getSocial().getId())
//                .user(userDto)
                .social(SocialPlatform.valueOf(userSocials.getSocial().getName()))
                .socialUserName(userSocials.getSocialUserName())
                .build();
    }

    public UserSocials saveUserSocial(UserSocials userSocials){
        return userSocialsRepository.save(userSocials);
    }

    public Optional<UserSocials> findUserSocial(Users user, Socials social){
        return userSocialsRepository.findByUserAndSocial(user, social);
    }

    public void deleteUserSocial(UserSocials userSocials){
        userSocialsRepository.delete(userSocials);
    }

//    private UserDto convertUserToDto(Users user) {
//        return userMapper.apply(user);
//    }
}
