package com.discoverme.backend.user;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    @GetMapping
    public ResponseEntity<Page<UserDto>> allUsers(Pageable pageable) {
        Page<Users> users = userService.findAll(pageable);
        List<UserDto> userDtos = users.stream().map(user -> {
            return this.userMapper.apply(user);
        }).collect(Collectors.toList());
        Page<UserDto> userDtosPage = new PageImpl(userDtos);
        return new ResponseEntity<>(userDtosPage, HttpStatus.OK);
    }
}
