package com.discoverme.backend.mail;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;

@ContextConfiguration(classes = {AltGMailService.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class AltGMailServiceDiffblueTest {
    @Autowired
    private AltGMailService altGMailService;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private TemplateEngine templateEngine;

    /**
     * Method under test:
     * {@link AltGMailService#sendSimpleMailMessage(String, String, String)}
     */
    @Test
    void testSendSimpleMailMessage() throws MailException {
        // Arrange
        doNothing().when(javaMailSender).send(Mockito.<SimpleMailMessage>any());

        // Act
        altGMailService.sendSimpleMailMessage("Name", "alice.liddell@example.org", "ABC123");

        // Assert
        verify(javaMailSender).send(isA(SimpleMailMessage.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendSimpleMailMessage(String, String, String)}
     */
    @Test
    void testSendSimpleMailMessage2() throws MailException {
        // Arrange
        doThrow(new RuntimeException(AltGMailService.NEW_USER_ACCOUNT_VERIFICATION)).when(javaMailSender)
                .send(Mockito.<SimpleMailMessage>any());

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> altGMailService.sendSimpleMailMessage("Name", "alice.liddell@example.org", "ABC123"));
        verify(javaMailSender).send(isA(SimpleMailMessage.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithAttachments(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithAttachments() throws MailException {
        // Arrange
        doNothing().when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act
        altGMailService.sendMimeMessageWithAttachments("Name", "alice.liddell@example.org", "ABC123");

        // Assert
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithAttachments(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithAttachments2() throws MailException {
        // Arrange
        doThrow(new RuntimeException(AltGMailService.UTF_8_ENCODING)).when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> altGMailService.sendMimeMessageWithAttachments("Name", "alice.liddell@example.org", "ABC123"));
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithAttachments(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithAttachments3() {
        // Arrange
        when(javaMailSender.createMimeMessage()).thenReturn(null);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> altGMailService.sendMimeMessageWithAttachments("Name", "alice.liddell@example.org", "ABC123"));
        verify(javaMailSender).createMimeMessage();
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithAttachments(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithAttachments4() throws MessagingException, MailException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setRecipient(Mockito.<Message.RecipientType>any(), Mockito.<Address>any());
        doNothing().when(mimeMessage).setContent(Mockito.<Multipart>any());
        doNothing().when(mimeMessage).setFrom(Mockito.<Address>any());
        doNothing().when(mimeMessage).setHeader(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(mimeMessage).setSubject(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act
        altGMailService.sendMimeMessageWithAttachments("Name", "alice.liddell@example.org", "ABC123");

        // Assert
        verify(mimeMessage).setRecipient(isA(Message.RecipientType.class), isA(Address.class));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(mimeMessage).setFrom(isA(Address.class));
        verify(mimeMessage).setHeader(eq("X-Priority"), eq("1"));
        verify(mimeMessage).setSubject(eq("New User Account Verification"), eq("UTF-8"));
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithAttachments(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithAttachments5() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setContent(Mockito.<Multipart>any());
        doNothing().when(mimeMessage).setFrom(Mockito.<Address>any());
        doNothing().when(mimeMessage).setHeader(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(mimeMessage).setSubject(Mockito.<String>any(), Mockito.<String>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService.sendMimeMessageWithAttachments("Name",
                AltGMailService.NEW_USER_ACCOUNT_VERIFICATION, "ABC123"));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(mimeMessage).setFrom(isA(Address.class));
        verify(mimeMessage).setHeader(eq("X-Priority"), eq("1"));
        verify(mimeMessage).setSubject(eq("New User Account Verification"), eq("UTF-8"));
        verify(javaMailSender).createMimeMessage();
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithAttachments(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithAttachments6() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setContent(Mockito.<Multipart>any());
        doNothing().when(mimeMessage).setFrom(Mockito.<Address>any());
        doNothing().when(mimeMessage).setHeader(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(mimeMessage).setSubject(Mockito.<String>any(), Mockito.<String>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService.sendMimeMessageWithAttachments("Name", "", "ABC123"));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(mimeMessage).setFrom(isA(Address.class));
        verify(mimeMessage).setHeader(eq("X-Priority"), eq("1"));
        verify(mimeMessage).setSubject(eq("New User Account Verification"), eq("UTF-8"));
        verify(javaMailSender).createMimeMessage();
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithEmbeddedFiles() throws MailException {
        // Arrange
        doNothing().when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act
        altGMailService.sendMimeMessageWithEmbeddedFiles("Name", "alice.liddell@example.org", "ABC123");

        // Assert
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithEmbeddedFiles2() throws MailException {
        // Arrange
        doThrow(new RuntimeException(AltGMailService.UTF_8_ENCODING)).when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> altGMailService.sendMimeMessageWithEmbeddedFiles("Name", "alice.liddell@example.org", "ABC123"));
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithEmbeddedFiles3() throws MailException {
        // Arrange
        doThrow(new MailAuthenticationException(AltGMailService.UTF_8_ENCODING)).when(javaMailSender)
                .send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> altGMailService.sendMimeMessageWithEmbeddedFiles("Name", "alice.liddell@example.org", "ABC123"));
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithEmbeddedFiles4() throws MessagingException, MailException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setRecipient(Mockito.<Message.RecipientType>any(), Mockito.<Address>any());
        doNothing().when(mimeMessage).setContent(Mockito.<Multipart>any());
        doNothing().when(mimeMessage).setFrom(Mockito.<Address>any());
        doNothing().when(mimeMessage).setHeader(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(mimeMessage).setSubject(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act
        altGMailService.sendMimeMessageWithEmbeddedFiles("Name", "alice.liddell@example.org", "ABC123");

        // Assert
        verify(mimeMessage).setRecipient(isA(Message.RecipientType.class), isA(Address.class));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(mimeMessage).setFrom(isA(Address.class));
        verify(mimeMessage).setHeader(eq("X-Priority"), eq("1"));
        verify(mimeMessage).setSubject(eq("New User Account Verification"), eq("UTF-8"));
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithEmbeddedFiles5() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setContent(Mockito.<Multipart>any());
        doNothing().when(mimeMessage).setFrom(Mockito.<Address>any());
        doNothing().when(mimeMessage).setHeader(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(mimeMessage).setSubject(Mockito.<String>any(), Mockito.<String>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService.sendMimeMessageWithEmbeddedFiles("Name",
                AltGMailService.NEW_USER_ACCOUNT_VERIFICATION, "ABC123"));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(mimeMessage).setFrom(isA(Address.class));
        verify(mimeMessage).setHeader(eq("X-Priority"), eq("1"));
        verify(mimeMessage).setSubject(eq("New User Account Verification"), eq("UTF-8"));
        verify(javaMailSender).createMimeMessage();
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithEmbeddedFiles6() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setContent(Mockito.<Multipart>any());
        doNothing().when(mimeMessage).setFrom(Mockito.<Address>any());
        doNothing().when(mimeMessage).setHeader(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(mimeMessage).setSubject(Mockito.<String>any(), Mockito.<String>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService.sendMimeMessageWithEmbeddedFiles("Name", "", "ABC123"));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(mimeMessage).setFrom(isA(Address.class));
        verify(mimeMessage).setHeader(eq("X-Priority"), eq("1"));
        verify(mimeMessage).setSubject(eq("New User Account Verification"), eq("UTF-8"));
        verify(javaMailSender).createMimeMessage();
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendMimeMessageWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendMimeMessageWithEmbeddedFiles7() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doThrow(new MailAuthenticationException(AltGMailService.UTF_8_ENCODING)).when(mimeMessage)
                .setContent(Mockito.<Multipart>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> altGMailService.sendMimeMessageWithEmbeddedFiles("Name", "alice.liddell@example.org", "ABC123"));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(javaMailSender).createMimeMessage();
    }

    /**
     * Method under test: {@link AltGMailService#sendHtmlEmail(EventDto)}
     */
    @Test
    void testSendHtmlEmail() {
        // Arrange, Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService.sendHtmlEmail(new EventDto()));
        assertThrows(RuntimeException.class, () -> altGMailService.sendHtmlEmail(null));
    }

    /**
     * Method under test: {@link AltGMailService#sendHtmlEmail(EventDto)}
     */
    @Test
    void testSendHtmlEmail2() {
        // Arrange
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        when(templateEngine.process(Mockito.<String>any(), Mockito.<IContext>any())).thenReturn("Process");

        // Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService
                .sendHtmlEmail(new EventDto("alice.liddell@example.org", "jane.doe@example.org", new HashMap<>())));
        verify(javaMailSender).createMimeMessage();
        verify(templateEngine).process(eq("emailtemplate"), isA(IContext.class));
    }

    /**
     * Method under test: {@link AltGMailService#sendHtmlEmail(EventDto)}
     */
    @Test
    void testSendHtmlEmail3() {
        // Arrange
        when(templateEngine.process(Mockito.<String>any(), Mockito.<IContext>any()))
                .thenThrow(new RuntimeException("name"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService
                .sendHtmlEmail(new EventDto("alice.liddell@example.org", "jane.doe@example.org", new HashMap<>())));
        verify(templateEngine).process(eq("emailtemplate"), isA(IContext.class));
    }

    /**
     * Method under test: {@link AltGMailService#sendHtmlEmail(EventDto)}
     */
    @Test
    void testSendHtmlEmail4() {
        // Arrange
        when(javaMailSender.createMimeMessage()).thenReturn(null);
        when(templateEngine.process(Mockito.<String>any(), Mockito.<IContext>any())).thenReturn("Process");

        // Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService
                .sendHtmlEmail(new EventDto("alice.liddell@example.org", "jane.doe@example.org", new HashMap<>())));
        verify(javaMailSender).createMimeMessage();
        verify(templateEngine).process(eq("emailtemplate"), isA(IContext.class));
    }

    /**
     * Method under test: {@link AltGMailService#sendHtmlEmail(EventDto)}
     */
    @Test
    void testSendHtmlEmail5() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setContent(Mockito.<Multipart>any());
        doNothing().when(mimeMessage).setHeader(Mockito.<String>any(), Mockito.<String>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(Mockito.<String>any(), Mockito.<IContext>any())).thenReturn("Process");

        // Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService
                .sendHtmlEmail(new EventDto("alice.liddell@example.org", "jane.doe@example.org", new HashMap<>())));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(mimeMessage).setHeader(eq("X-Priority"), eq("1"));
        verify(javaMailSender).createMimeMessage();
        verify(templateEngine).process(eq("emailtemplate"), isA(IContext.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendHtmlEmailWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendHtmlEmailWithEmbeddedFiles() throws MailException {
        // Arrange
        doNothing().when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        when(templateEngine.process(Mockito.<String>any(), Mockito.<IContext>any())).thenReturn("Process");

        // Act
        altGMailService.sendHtmlEmailWithEmbeddedFiles("Name", "alice.liddell@example.org", "ABC123");

        // Assert
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
        verify(templateEngine).process(eq("emailtemplate"), isA(IContext.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendHtmlEmailWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendHtmlEmailWithEmbeddedFiles2() {
        // Arrange
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        when(templateEngine.process(Mockito.<String>any(), Mockito.<IContext>any()))
                .thenThrow(new RuntimeException(AltGMailService.UTF_8_ENCODING));

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> altGMailService.sendHtmlEmailWithEmbeddedFiles("Name", "alice.liddell@example.org", "ABC123"));
        verify(javaMailSender).createMimeMessage();
        verify(templateEngine).process(eq("emailtemplate"), isA(IContext.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendHtmlEmailWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendHtmlEmailWithEmbeddedFiles3() {
        // Arrange
        when(javaMailSender.createMimeMessage()).thenReturn(null);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> altGMailService.sendHtmlEmailWithEmbeddedFiles("Name", "alice.liddell@example.org", "ABC123"));
        verify(javaMailSender).createMimeMessage();
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendHtmlEmailWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendHtmlEmailWithEmbeddedFiles4() throws MessagingException, MailException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setRecipient(Mockito.<Message.RecipientType>any(), Mockito.<Address>any());
        doNothing().when(mimeMessage).setContent(Mockito.<Multipart>any());
        doNothing().when(mimeMessage).setFrom(Mockito.<Address>any());
        doNothing().when(mimeMessage).setHeader(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(mimeMessage).setSubject(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(Mockito.<String>any(), Mockito.<IContext>any())).thenReturn("Process");

        // Act
        altGMailService.sendHtmlEmailWithEmbeddedFiles("Name", "alice.liddell@example.org", "ABC123");

        // Assert
        verify(mimeMessage).setRecipient(isA(Message.RecipientType.class), isA(Address.class));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(mimeMessage).setFrom(isA(Address.class));
        verify(mimeMessage).setHeader(eq("X-Priority"), eq("1"));
        verify(mimeMessage).setSubject(eq("New User Account Verification"), eq("UTF-8"));
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
        verify(templateEngine).process(eq("emailtemplate"), isA(IContext.class));
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendHtmlEmailWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendHtmlEmailWithEmbeddedFiles5() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setContent(Mockito.<Multipart>any());
        doNothing().when(mimeMessage).setFrom(Mockito.<Address>any());
        doNothing().when(mimeMessage).setHeader(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(mimeMessage).setSubject(Mockito.<String>any(), Mockito.<String>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService.sendHtmlEmailWithEmbeddedFiles("Name",
                AltGMailService.NEW_USER_ACCOUNT_VERIFICATION, "ABC123"));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(mimeMessage).setFrom(isA(Address.class));
        verify(mimeMessage).setHeader(eq("X-Priority"), eq("1"));
        verify(mimeMessage).setSubject(eq("New User Account Verification"), eq("UTF-8"));
        verify(javaMailSender).createMimeMessage();
    }

    /**
     * Method under test:
     * {@link AltGMailService#sendHtmlEmailWithEmbeddedFiles(String, String, String)}
     */
    @Test
    void testSendHtmlEmailWithEmbeddedFiles6() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(mimeMessage).setContent(Mockito.<Multipart>any());
        doNothing().when(mimeMessage).setFrom(Mockito.<Address>any());
        doNothing().when(mimeMessage).setHeader(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(mimeMessage).setSubject(Mockito.<String>any(), Mockito.<String>any());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> altGMailService.sendHtmlEmailWithEmbeddedFiles("Name", "", "ABC123"));
        verify(mimeMessage).setContent(isA(Multipart.class));
        verify(mimeMessage).setFrom(isA(Address.class));
        verify(mimeMessage).setHeader(eq("X-Priority"), eq("1"));
        verify(mimeMessage).setSubject(eq("New User Account Verification"), eq("UTF-8"));
        verify(javaMailSender).createMimeMessage();
    }
}
