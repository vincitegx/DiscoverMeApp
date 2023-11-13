package com.discoverme.backend.mail;


/**
 *
 * @author TEGA
 */
public interface MailService {
    void sendSimpleMailMessage(String name, String to, String token);
    void sendMimeMessageWithAttachments(String name, String to, String token);
    void sendMimeMessageWithEmbeddedFiles(String name, String to, String token);
//    void sendHtmlEmail(String name, String to, String token);
    void sendHtmlEmail(EventDto eventDto);
    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);
}
