package com.ecommerce.mail.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailDispatchService {
    private  final JavaMailSender javaMailSender;

    public MailDispatchService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String to, String subject, String body){
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom("noreply@ecommerce.com");
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        javaMailSender.send(msg);

    }
}
