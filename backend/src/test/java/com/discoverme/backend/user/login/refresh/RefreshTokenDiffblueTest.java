package com.discoverme.backend.user.login.refresh;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RefreshToken.class})
@ExtendWith(SpringExtension.class)
class RefreshTokenDiffblueTest {
    @Autowired
    private RefreshToken refreshToken;

    /**
     * Method under test: {@link RefreshToken#onCreate()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testOnCreate() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests that are time-sensitive.
        //   The assertions don't pass when run at an alternate date, time, and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   See Working with code R031 (https://diff.blue/R031) for details.

        // Arrange and Act
        (new RefreshToken()).onCreate();
    }
}
