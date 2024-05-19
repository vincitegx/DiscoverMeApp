package com.discoverme.backend.social;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialsService {

    private final SocialsRepository socialsRepository;

    public List<Socials> getAllSocials() {
        return socialsRepository.findAll();
    }

    public Socials getSocialByPlatform(SocialPlatform platform){
        return socialsRepository.findByNameIgnoreCase(platform.name());
    }

    public Socials getSocialById(Long id){
        return socialsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No social found with this ID"));
    }
}
