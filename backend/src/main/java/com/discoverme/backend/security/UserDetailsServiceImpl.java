package com.discoverme.backend.security;

import com.discoverme.backend.user.UserRepository;
import com.discoverme.backend.user.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Users user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with phone number: " + phoneNumber));
        return new org.springframework.security.core.userdetails.User(user.getStageName(), user.getPassword(), user.getEnabled(), true, true, user.getNonLocked(), getAuthorities(user));
//        return new UserDetailsImpl(user);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }
}