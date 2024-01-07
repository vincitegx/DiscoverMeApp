package com.discoverme.backend.user.social;

import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.Users;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserSocialsController {

    private final UserSocialsService userSocialsService;

    @GetMapping("socials")
    public ResponseEntity<List<UserSocialsDto>> userSocials() {
        List<UserSocialsDto> userSocials = userSocialsService.findAllUserSocials();
        return new ResponseEntity<>(userSocials, HttpStatus.OK);
    }
}
