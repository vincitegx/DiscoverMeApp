package com.discoverme.backend.verification;

import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationService {
    private final UserService userService;
    public String fetchAndEnableUser(Long userId){
       Users user = userService.findById(userId).orElseThrow(()-> new UsernameNotFoundException("No user found with this id"));
       user.setEnabled(true);
       user.setNonLocked(true);
       user = userService.saveUser(user);
       if(user.getEnabled() && user.getNonLocked()){
           return "User account has been activated !!!";
       }else{
           return "Sorry user account could not be activated, Please try again later :(";
       }
    }
}
