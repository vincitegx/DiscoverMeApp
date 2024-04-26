package com.discoverme.backend.user.verification;

import static org.mockito.Mockito.when;

import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {VerificationController.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class VerificationControllerDiffblueTest {
    @Autowired
    private VerificationController verificationController;

    @MockBean
    private VerificationService verificationService;

    /**
     * Method under test: {@link VerificationController#verifyUserAccount(String)}
     */
    @Test
    void testVerifyUserAccount() throws Exception {
        // Arrange
        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        when(verificationService.verifyEmail(Mockito.<String>any())).thenReturn(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/auth/verify")
                .param("token", "foo");

        // Act and Assert
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(verificationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }
}
