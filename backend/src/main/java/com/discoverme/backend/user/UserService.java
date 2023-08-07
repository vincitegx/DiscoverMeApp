package com.discoverme.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Users findUserByPhoneNumber(String phoneNumber){
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(()-> new UserException("No user found with this phone number"));
    }

    public Users saveUser(Users user){
        return userRepository.save(user);
    }
}
