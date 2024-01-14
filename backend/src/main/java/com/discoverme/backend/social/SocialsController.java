package com.discoverme.backend.social;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/socials")
@RequiredArgsConstructor
public class SocialsController {
    private final SocialsService socialsService;

    @GetMapping
    public ResponseEntity<List<Socials>> getAllSocials(){
        return new ResponseEntity<>(socialsService.getAllSocials(), HttpStatus.OK);
    }
}
