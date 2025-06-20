//package com.event_registration.lk.service.impl;
//
//import com.event_registration.lk.service.EmailService;
//import org.springframework.context.annotation.Primary;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//@Primary
//public class EmailServiceImpl implements EmailService {
//
//    private final JavaMailSender javaMailSender;
//
//    public EmailServiceImpl(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//
//    @Override
//    public void sendEmail(String to, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//    }
//}
