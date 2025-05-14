package org.example.spring_ecommerce.infrastructure.configuration.configMail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.seuprovedor.com"); // Ex: smtp.gmail.com
        mailSender.setPort(587);
        mailSender.setUsername("seu-email@dominio.com");
        mailSender.setPassword("sua-senha");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true"); // Apenas para desenvolvimento

        return mailSender;
    }
}
