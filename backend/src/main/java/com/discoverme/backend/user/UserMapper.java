package com.discoverme.backend.user;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper implements Function<Users, UserDto> {
    @Override
    public UserDto apply(Users users) {
        return null;
    }
}
