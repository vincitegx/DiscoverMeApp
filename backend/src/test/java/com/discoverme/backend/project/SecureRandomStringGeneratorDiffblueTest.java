package com.discoverme.backend.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecureRandomStringGenerator.class})
@ExtendWith(SpringExtension.class)
class SecureRandomStringGeneratorDiffblueTest {
    @Autowired
    private SecureRandomStringGenerator secureRandomStringGenerator;

    /**
     * Method under test: {@link SecureRandomStringGenerator#apply(int)}
     */
    @Test
    void testApply() {
        // Arrange, Act and Assert
        assertEquals("", secureRandomStringGenerator.apply(0));
    }
}
