package com.discoverme.backend.user;

import static org.mockito.Mockito.mock;

import com.discoverme.backend.security.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {GoogleController.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class GoogleControllerDiffblueTest {
    @MockBean
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private GoogleController googleController;

    @MockBean
    private HttpServletResponse httpServletResponse;

    @MockBean
    private UserMapper userMapper;

    /**
     * Method under test: {@link GoogleController#auth()}
     */
    @Test
    void testAuth() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/url");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(googleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"authURL\":\"https://accounts.google.com/o/oauth2/auth?access_type=offline&client_id=$%7Bspring.security"
                                        + ".oauth2.resourceserver.opaque-token.client-id%7D&redirect_uri=https://localhost:4200&response_type"
                                        + "=code&scope=email%20profile%20openid\"}"));
    }

    /**
     * Method under test: {@link GoogleController#callback(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCallback() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        // Arrange
        Response servletResponse = new Response();
        UserRepository userRepository = mock(UserRepository.class);
        CustomOAuth2UserService customOAuth2UserService = new CustomOAuth2UserService(userRepository,
                new BCryptPasswordEncoder());

        // Act
        (new GoogleController(servletResponse, customOAuth2UserService, new UserMapper())).callback("Code");
    }

    /**
     * Method under test: {@link GoogleController#auth()}
     */
    @Test
    void testAuth2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/url", "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(googleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"authURL\":\"https://accounts.google.com/o/oauth2/auth?access_type=offline&client_id=$%7Bspring.security"
                                        + ".oauth2.resourceserver.opaque-token.client-id%7D&redirect_uri=https://localhost:4200&response_type"
                                        + "=code&scope=email%20profile%20openid\"}"));
    }
}
