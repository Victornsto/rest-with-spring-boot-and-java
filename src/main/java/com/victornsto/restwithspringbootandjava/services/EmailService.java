package com.victornsto.restwithspringbootandjava.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victornsto.restwithspringbootandjava.config.EmailConfig;
import com.victornsto.restwithspringbootandjava.dto.v1.request.EmailRequestDto;
import com.victornsto.restwithspringbootandjava.mail.EmailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class EmailService {
    private final EmailSender emailSender;
    private final EmailConfig emailConfig;

    public EmailService(EmailSender emailSender, EmailConfig emailConfig) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }
    public void sendEmail(EmailRequestDto emailRequestDto) {
        emailSender
                .to(emailRequestDto.getTo())
                .withSubject(emailRequestDto.getSubject())
                .withBody(emailRequestDto.getBody())
                .send(emailConfig);
    }

    public void sendEmailWithAttachment(String emailRequestJson, MultipartFile multipartFile) {
        File tempFile = null;
        try {
            EmailRequestDto emailRequestDto = new ObjectMapper().readValue(emailRequestJson, EmailRequestDto.class);
            tempFile = File.createTempFile("attachment", multipartFile.getOriginalFilename());
            multipartFile.transferTo(tempFile);
            emailSender
                    .to(emailRequestDto.getTo())
                    .withSubject(emailRequestDto.getSubject())
                    .withBody(emailRequestDto.getBody())
                    .attach(tempFile.getAbsolutePath())
                    .send(emailConfig);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing email request JSON!" ,e);
        } catch (IOException e) {
            throw new RuntimeException("Error processing email attachment!", e);
        } finally {
            if (tempFile != null && tempFile.exists()) tempFile.delete();
        }
    }
}
