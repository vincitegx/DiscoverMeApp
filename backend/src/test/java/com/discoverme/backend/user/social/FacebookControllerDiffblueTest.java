package com.discoverme.backend.user.social;

import com.discoverme.backend.social.SocialsService;
import com.discoverme.backend.social.facebook.FacebookController;
import com.discoverme.backend.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ContextConfiguration(classes = {FacebookController.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class FacebookControllerDiffblueTest {
    @Autowired
    private FacebookController facebookController;

    @MockBean
    private HttpServletResponse httpServletResponse;

    /**
     * Method under test: {@link FacebookController#auth()}
     */
    @Test
    void testAuth() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/fb/url");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(facebookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"authURL\":\"https://www.facebook.com/dialog/oauth?client_id=%24%7Bspring.security.oauth2.facebook.app"
                                        + "-id%7D&redirect_uri=https%3A%2F%2Flocalhost%3A4200%2Fprofile&scope=public_profile%2Cemail%2Cpublic"
                                        + "_profile%2Cpages_manage_posts\"}"));
    }

    @Test
@Disabled("TODO: Complete this test")
void testCallback() {
    // Arrange
    // Create mock instances of the required services
    UserService userService = mock(UserService.class);
    SocialsService socialsService = mock(SocialsService.class);
    UserSocialsService userSocialsService = mock(UserSocialsService.class);

    // Act and Assert
    // Call the method under test with the mock instances
    FacebookController facebookController = new FacebookController(userService, socialsService, userSocialsService);
    String result = String.valueOf(facebookController.callback("Code"));

    // Add assertions to verify the expected behavior
    // For example, you can verify that the callback method returns the expected result
    assertEquals("Expected result", result);
}

    /**
     * Method under test: {@link FacebookController#auth()}
     */
    @Test
    void testAuth2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/fb/url");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(facebookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"authURL\":\"https://www.facebook.com/dialog/oauth?client_id=%24%7Bspring.security.oauth2.facebook.app"
                                        + "-id%7D&redirect_uri=https%3A%2F%2Flocalhost%3A4200%2Fprofile&scope=public_profile%2Cemail%2Cpublic"
                                        + "_profile%2Cpages_manage_posts\"}"));
    }
}
