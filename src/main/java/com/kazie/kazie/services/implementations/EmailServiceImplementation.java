package com.kazie.kazie.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailServiceImplementation {
    public EmailServiceImplementation(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private final JavaMailSender mailSender;

    public void envoyerTest(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Test d'envoi avec Gmail SMTP");
        message.setText("Bonjour ! Ce message est envoyé avec Spring Boot via Gmail SMTP.");
        message.setFrom("ton.email@gmail.com");

        mailSender.send(message);
    }
}

