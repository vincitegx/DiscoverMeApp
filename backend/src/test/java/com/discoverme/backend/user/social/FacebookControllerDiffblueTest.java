package com.discoverme.backend.user.social;

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

    /**
     * Method under test: {@link FacebookController#callback(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCallback() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "com.restfb.DefaultFacebookClient.obtainUserAccessToken(String, String, String, String)" because "this.facebookClient" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   java.lang.NullPointerException: Cannot invoke "com.restfb.DefaultFacebookClient.obtainUserAccessToken(String, String, String, String)" because "this.facebookClient" is null
        //       at com.discoverme.backend.user.social.FacebookController.callback(FacebookController.java:59)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new FacebookController(new Response())).callback("Code");
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
