package com.discoverme.backend.user.social;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserSocialsService {
    private final UserSocialsRepository userSocialsRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    public List<UserSocialsDto> findAllUserSocials() {
        List<UserSocials> userSocials = userSocialsRepository.findByUser(userService.getCurrentUser());
        return userSocials.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserSocialsDto convertToDto(UserSocials userSocials) {
        UserDto userDto = convertUserToDto(userSocials.getUser());
        return UserSocialsDto.builder()
                .id(userSocials.getId())
                .user(userDto)
                .social(SocialPlatform.valueOf(userSocials.getSocial().getName()))
                .userName(userSocials.getUserName())
                .build();
    }

    private UserDto convertUserToDto(Users user) {
        return userMapper.apply(user);
    }
}
