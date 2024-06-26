package com.discoverme.backend.user;

import com.discoverme.backend.security.JWTAuthenticationFilter;
import com.discoverme.backend.user.login.refresh.RefreshToken;
import com.discoverme.backend.user.login.refresh.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.RefreshFailedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    
    @PutMapping("block")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<String> blockUserAccount(@RequestParam @NonNull String id){
        Long userId = Long.parseLong(id);
        String response = userService.fetchAndDisableUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("profile")
    public ResponseEntity<UserDto> updateProfile(@RequestBody @Valid ProfileRequest request) {
        System.out.println("Entered edit profile controller");
        UserDto user = userService.updateProfile(request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> all(Pageable pageable) {
        Page<Users> users = userService.findAll(pageable);
        List<UserDto> userDtos = users.stream().map(this.userMapper).collect(Collectors.toList());
        Page<UserDto> userDtosPage = new PageImpl(userDtos);
        return new ResponseEntity<>(userDtosPage, HttpStatus.OK);
    }

    @GetMapping("role-user")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> allUsers(Pageable pageable) {
        Page<Users> users = userService.findAllByRoleUser(pageable);
        List<UserDto> userDtos = users.stream().map(user -> this.userMapper.apply(user)).collect(Collectors.toList());
        Page<UserDto> userDtosPage = new PageImpl(userDtos);
        return new ResponseEntity<>(userDtosPage, HttpStatus.OK);
    }

    @GetMapping("me")
    public ResponseEntity<UserDto> me() {
        Users user = userService.getCurrentUser();
        UserDto userDto = this.userMapper.apply(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@RequestParam("id") String id) {
        Long userId = Long.parseLong(id);
        Users user = userService.findById(userId).orElseThrow(()-> new UserException("User not found with ID"));
        UserDto userDto = this.userMapper.apply(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("admin")
    @Secured({"ROLE_SUPER-ADMIN"})
    public ResponseEntity<Void> removeAdmin(@RequestParam String userId) {
        Long id = Long.parseLong(userId);
        userService.removeAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
