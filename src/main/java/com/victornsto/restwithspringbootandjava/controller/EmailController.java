package com.victornsto.restwithspringbootandjava.controller;

import com.victornsto.restwithspringbootandjava.controller.docs.EmailControlerDocs;
import com.victornsto.restwithspringbootandjava.dto.v1.request.EmailRequestDto;
import com.victornsto.restwithspringbootandjava.services.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController implements EmailControlerDocs {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    @Override
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDto emailRequestDto) {
        emailService.sendEmail(emailRequestDto);
        return new ResponseEntity<>("e-mail sent with success!",HttpStatus.OK);
    }

    @PostMapping("/withAttachment")
    @Override
    public ResponseEntity<String> sendEmailWithAttachment(
            @RequestParam("emailRequest") String emailRequestJson,
            @RequestParam("attachment") MultipartFile multipartFile) {
        emailService.sendEmailWithAttachment(emailRequestJson, multipartFile);
        return new ResponseEntity<>("Email with attachment sent successfully", HttpStatus.OK);
    }

}
