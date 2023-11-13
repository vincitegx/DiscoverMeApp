package com.discoverme.backend.mail;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author TEGA
 */
@Service
@AllArgsConstructor
public class MailContentBuilder {
    private final TemplateEngine templateEngine;

    public String build(Map<?,?> data) {
        Context context = new Context();
        context.setVariable("token", data.get("token"));
        context.setVariable("expiresAt", data.get("expiresAt"));
        context.setVariable("message", data.get("msg"));
        context.setVariable("subject", data.get("subject"));
        return templateEngine.process(data.get("template").toString(), context);

    }
}
