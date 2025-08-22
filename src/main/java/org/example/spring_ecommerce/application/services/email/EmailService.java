package org.example.spring_ecommerce.application.services.email;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.inBound.dtos.email.EmailDto;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {


    private final JavaMailSender mailSender;

    public void sendEmail(EmailDto emailDto) {

        var message = new SimpleMailMessage();

        message.setFrom("comecommerce@ecommerce.com.br");
        message.setTo(emailDto.destinatario());
        message.setSubject(emailDto.assunto());
        message.setText(emailDto.corpo());

        mailSender.send(message);
    }
}
