package com.discoverme.backend.user.social;

import static org.mockito.Mockito.when;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.user.UserDto;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserSocialsController.class})
@ExtendWith(SpringExtension.class)
class UserSocialsControllerDiffblueTest {
    @Autowired
    private UserSocialsController userSocialsController;

    @MockBean
    private UserSocialsService userSocialsService;

    /**
     * Method under test: {@link UserSocialsController#userSocials()}
     */
    @Test
    void testUserSocials() throws Exception {
        // Arrange
        when(userSocialsService.findAllUserSocials()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/socials");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userSocialsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserSocialsController#userSocials()}
     */
    @Test
    void testUserSocials2() throws Exception {
        // Arrange
        ArrayList<UserSocialsDto> userSocialsDtoList = new ArrayList<>();
        UserSocialsDto.UserSocialsDtoBuilder socialResult = UserSocialsDto.builder().id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();
        userSocialsDtoList.add(buildResult);
        when(userSocialsService.findAllUserSocials()).thenReturn(userSocialsDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/socials");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userSocialsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"user\":{\"id\":1,\"stageName\":\"Stage Name\",\"email\":\"jane.doe@example.org\",\"role\":\"Role\"},\"social"
                                        + "\":\"FACEBOOK\",\"userName\":\"janedoe\"}]"));
    }
}
