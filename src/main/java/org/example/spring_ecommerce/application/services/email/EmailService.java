package org.example.spring_ecommerce.application.services.email;


import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.inBound.dtos.EmailDto;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(EmailDto emailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("uolEcommerce@compasso.com.br");
        message.setTo(emailDto.to());
        message.setSubject(emailDto.subject());
        message.setText(emailDto.body());
        mailSender.send(message);
    }
}
