package com.discoverme.backend.social;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SocialsService.class})
@ExtendWith(SpringExtension.class)
class SocialsServiceDiffblueTest {
    @MockBean
    private SocialsRepository socialsRepository;

    @Autowired
    private SocialsService socialsService;

    /**
     * Method under test: {@link SocialsService#getAllSocials()}
     */
    @Test
    void testGetAllSocials() {
        // Arrange
        ArrayList<Socials> socialsList = new ArrayList<>();
        when(socialsRepository.findAll()).thenReturn(socialsList);

        // Act
        List<Socials> actualAllSocials = socialsService.getAllSocials();

        // Assert
        verify(socialsRepository).findAll();
        assertTrue(actualAllSocials.isEmpty());
        assertSame(socialsList, actualAllSocials);
    }
}
