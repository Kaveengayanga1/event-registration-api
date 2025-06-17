//package com.event_registration.lk.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//public class MailConfig {
//
//    @Value("${spring.mail.port}")
//    private String port;
//    @Value("${spring.mail.username}")
//    private String username;
//    @Value("${spring.mail.password}" )
//    private String password;
//    @Value("${spring.mail.host}")
//    private String host;
//
//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(host);
//        mailSender.setPort(Integer.parseInt(port));
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//
//        return mailSender;
//    }
//}
