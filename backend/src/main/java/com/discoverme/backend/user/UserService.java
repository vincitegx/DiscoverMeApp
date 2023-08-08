package com.discoverme.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Users findByStageName(String stageName) {
        return userRepository.findByStageName(stageName)
                .orElseThrow(()-> new UserException("No user found with this stage name"));
    }

    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    public Page<Users> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
