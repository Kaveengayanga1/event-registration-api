//package com.event_registration.lk.listener;
//
//import com.event_registration.lk.service.EmailService;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SignupEventConsumer {
//
//    private final EmailService emailService;
//
//
//    public SignupEventConsumer(EmailService emailService) {
//        this.emailService = emailService;
//    }
//
//    @KafkaListener(topics = "user-signup-topic", groupId = "user-signup-group")
//    public void consumeUserSignupEvent(String email){
//        String subject = "User Signup";
//        String body = "User Signup successful";
//
//        emailService.sendEmail(email, subject, body);
//
//    }
//
//}
