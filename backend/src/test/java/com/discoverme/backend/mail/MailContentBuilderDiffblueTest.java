package com.discoverme.backend.mail;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.thymeleaf.TemplateEngine;

@ContextConfiguration(classes = {MailContentBuilder.class})
@ExtendWith(SpringExtension.class)
class MailContentBuilderDiffblueTest {
    @Autowired
    private MailContentBuilder mailContentBuilder;

    @MockBean
    private TemplateEngine templateEngine;

    /**
     * Method under test: {@link MailContentBuilder#build(Map)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testBuild() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.toString()" because the return value of "com.diffblue.cover.agent.readwrite.RuntimeWrappers.map$get(java.util.Map, Object)" is null
        //       at com.discoverme.backend.mail.MailContentBuilder.build(MailContentBuilder.java:24)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        mailContentBuilder.build(new HashMap<>());
    }
}
