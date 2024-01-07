package com.discoverme.backend.project;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.function.IntFunction;

@Component
public class SecureRandomStringGenerator implements IntFunction<String> {

    @Override
    public String apply(int value) {
        return RandomStringUtils.random(value, 0, 0, true, true, null, new SecureRandom());
    }
}
