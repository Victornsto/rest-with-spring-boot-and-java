package com.victornsto.restwithspringbootandjava.mail;

import com.victornsto.restwithspringbootandjava.config.EmailConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Component
public class EmailSender {
    private final JavaMailSender mailSender;
    private String to;
    private String subject;
    private String body;
    private List<InternetAddress> recipients;
    private File attachment;
    Logger logger = LoggerFactory.getLogger(EmailSender.class);

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public EmailSender to(String to) {
        this.to = to;
        this.recipients = getRecipients(to);
        return this;
    }

    private List<InternetAddress> getRecipients(String to) {
        if (to == null || to.isBlank()) {
            return List.of();
        }
        return Arrays.stream(to.split(";"))
                .map(String::trim)
                .filter(email -> !email.isEmpty())
                .map(email -> {
                    try {
                        return new InternetAddress(email);
                    } catch (Exception e) {
                        throw new RuntimeException("Invalid email address: " + email, e);
                    }
                })
                .collect(Collectors.toList());
    }

    public EmailSender withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailSender withBody(String body) {
        this.body = body;
        return this;
    }

    public EmailSender attach(String fileDir) {
        this.attachment = new File(fileDir);
        return this;
    }

    public void send(EmailConfig config) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            System.out.println(config.getUsername());
            helper.setFrom(config.getUsername());
            helper.setTo(recipients.toArray(new InternetAddress[0]));
            helper.setSubject(subject);
            helper.setText(body, true);
            if (attachment != null && attachment.exists()) {
                helper.addAttachment(attachment.getName(), attachment);
            }
            mailSender.send(message);
            logger.info("Email sent %s with subject '%s' to %n", to, subject);
            reset();
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to create email message", e);
        }
    }

    private void reset() {
        this.to = null;
        this.subject = null;
        this.body = null;
        this.recipients = new ArrayList<>();
        this.attachment = null;
    }

}
