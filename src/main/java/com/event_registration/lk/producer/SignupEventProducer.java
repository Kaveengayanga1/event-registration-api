//package com.event_registration.lk.producer;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SignupEventProducer {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    @Value("${spring.app.kafka.topic.user-signup}")
//    private String topic;
//
//    public SignupEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void publishUserSignupEvent(String email){
//        kafkaTemplate.send(topic, email);
//    }
//}
