package com.discoverme.backend.mail;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.api.client.googleapis.testing.auth.oauth2.MockTokenServerTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GMailService.class})
@ExtendWith(SpringExtension.class)
class GMailServiceDiffblueTest {
    @Autowired
    private GMailService gMailService;

    /**
     * Method under test: {@link GMailService#sendEmail(EventDto)}
     */
    @Test
    void testSendEmail() throws MailException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        MockTokenServerTransport transport = new MockTokenServerTransport("https://example.org/example");
        GMailService gMailService = new GMailService(
                new Gmail(transport, GsonFactory.getDefaultInstance(), mock(HttpRequestInitializer.class)));

        EventDto eventDto = new EventDto();
        eventDto.setTo("");
        eventDto.setFrom("From");

        // Act and Assert
        assertThrows(MailSendException.class, () -> gMailService.sendEmail(eventDto));
    }

    /**
     * Method under test: {@link GMailService#sendEmail(EventDto)}
     */
    @Test
    void testSendEmail2() throws MailException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        MockTokenServerTransport transport = new MockTokenServerTransport("https://example.org/example");
        GMailService gMailService = new GMailService(
                new Gmail(transport, GsonFactory.getDefaultInstance(), mock(HttpRequestInitializer.class)));

        EventDto eventDto = new EventDto();
        eventDto.setTo("");
        eventDto.setFrom("");

        // Act and Assert
        assertThrows(MailSendException.class, () -> gMailService.sendEmail(eventDto));
    }

    /**
     * Method under test: {@link GMailService#sendEmail(EventDto)}
     */
    @Test
    void testSendEmail3() throws MailException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        MockTokenServerTransport transport = new MockTokenServerTransport("https://example.org/example");
        GMailService gMailService = new GMailService(
                new Gmail(transport, GsonFactory.getDefaultInstance(), mock(HttpRequestInitializer.class)));

        EventDto eventDto = new EventDto();
        eventDto.setTo("");
        eventDto.setFrom("jane.doe@example.orgjane.doe@example.org");

        // Act and Assert
        assertThrows(MailSendException.class, () -> gMailService.sendEmail(eventDto));
    }

    /**
     * Method under test: {@link GMailService#sendEmail(EventDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendEmail4() throws MailException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.discoverme.backend.mail.GMailService
        //   See https://diff.blue/R027 to resolve this issue.

        // Arrange and Act
        gMailService.sendEmail(new EventDto());
    }

    /**
     * Method under test: {@link GMailService#sendMessages(Message)}
     */
    @Test
    void testSendMessages() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Gmail.Users.Messages messages = mock(Gmail.Users.Messages.class);
        when(messages.send(Mockito.<String>any(), Mockito.<Message>any()))
                .thenThrow(new IOException("davidogbodu3056@gmail.com"));
        Gmail.Users users = mock(Gmail.Users.class);
        when(users.messages()).thenReturn(messages);
        Gmail service = mock(Gmail.class);
        when(service.users()).thenReturn(users);
        GMailService gMailService = new GMailService(service);

        // Act and Assert
        assertThrows(MailSendException.class, () -> gMailService.sendMessages(new Message()));
        verify(service).users();
        verify(users).messages();
        verify(messages).send(eq("davidogbodu3056@gmail.com"), isA(Message.class));
    }

    /**
     * Method under test: {@link GMailService#sendMessages(Message)}
     */
    @Test
    void testSendMessages2() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Gmail.Users.Messages.Send send = mock(Gmail.Users.Messages.Send.class);
        Message message = new Message();
        when(send.execute()).thenReturn(message);
        Gmail.Users.Messages messages = mock(Gmail.Users.Messages.class);
        when(messages.send(Mockito.<String>any(), Mockito.<Message>any())).thenReturn(send);
        Gmail.Users users = mock(Gmail.Users.class);
        when(users.messages()).thenReturn(messages);
        Gmail service = mock(Gmail.class);
        when(service.users()).thenReturn(users);
        GMailService gMailService = new GMailService(service);

        // Act
        Message actualSendMessagesResult = gMailService.sendMessages(new Message());

        // Assert
        verify(send).execute();
        verify(service).users();
        verify(users).messages();
        verify(messages).send(eq("davidogbodu3056@gmail.com"), isA(Message.class));
        assertTrue(actualSendMessagesResult.isEmpty());
        assertSame(message, actualSendMessagesResult);
    }

    /**
     * Method under test: {@link GMailService#sendMessages(Message)}
     */
    @Test
    void testSendMessages3() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Gmail.Users.Messages.Send send = mock(Gmail.Users.Messages.Send.class);
        when(send.execute()).thenThrow(new IOException("davidogbodu3056@gmail.com"));
        Gmail.Users.Messages messages = mock(Gmail.Users.Messages.class);
        when(messages.send(Mockito.<String>any(), Mockito.<Message>any())).thenReturn(send);
        Gmail.Users users = mock(Gmail.Users.class);
        when(users.messages()).thenReturn(messages);
        Gmail service = mock(Gmail.class);
        when(service.users()).thenReturn(users);
        GMailService gMailService = new GMailService(service);

        // Act and Assert
        assertThrows(MailSendException.class, () -> gMailService.sendMessages(new Message()));
        verify(send).execute();
        verify(service).users();
        verify(users).messages();
        verify(messages).send(eq("davidogbodu3056@gmail.com"), isA(Message.class));
    }

    /**
     * Method under test: {@link GMailService#sendMessages(Message)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendMessages4() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.discoverme.backend.mail.GMailService
        //   See https://diff.blue/R027 to resolve this issue.

        // Arrange and Act
        gMailService.sendMessages(new Message());
    }
}
