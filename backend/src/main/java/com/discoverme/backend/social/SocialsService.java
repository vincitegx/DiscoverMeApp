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
}
