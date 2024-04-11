package com.discoverme.backend.management;

import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityMetersService.class})
@ExtendWith(SpringExtension.class)
class SecurityMetersServiceDiffblueTest {
    @MockBean
    private MeterRegistry meterRegistry;

    @Autowired
    private SecurityMetersService securityMetersService;

    /**
     * Method under test: {@link SecurityMetersService#trackTokenInvalidSignature()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTrackTokenInvalidSignature() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "io.micrometer.core.instrument.Counter.increment()" because "this.tokenInvalidSignatureCounter" is null
        //       at com.discoverme.backend.management.SecurityMetersService.trackTokenInvalidSignature(SecurityMetersService.java:37)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        securityMetersService.trackTokenInvalidSignature();
    }

    /**
     * Method under test: {@link SecurityMetersService#trackTokenExpired()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTrackTokenExpired() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "io.micrometer.core.instrument.Counter.increment()" because "this.tokenExpiredCounter" is null
        //       at com.discoverme.backend.management.SecurityMetersService.trackTokenExpired(SecurityMetersService.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        securityMetersService.trackTokenExpired();
    }

    /**
     * Method under test: {@link SecurityMetersService#trackTokenUnsupported()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTrackTokenUnsupported() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "io.micrometer.core.instrument.Counter.increment()" because "this.tokenUnsupportedCounter" is null
        //       at com.discoverme.backend.management.SecurityMetersService.trackTokenUnsupported(SecurityMetersService.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        securityMetersService.trackTokenUnsupported();
    }

    /**
     * Method under test: {@link SecurityMetersService#trackTokenMalformed()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTrackTokenMalformed() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "io.micrometer.core.instrument.Counter.increment()" because "this.tokenMalformedCounter" is null
        //       at com.discoverme.backend.management.SecurityMetersService.trackTokenMalformed(SecurityMetersService.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        securityMetersService.trackTokenMalformed();
    }
}
