package com.saintrivers.messaging.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.saintrivers.messaging.model.Message;

@Component
public class MessageListener {

    @Value("${orderup.kafka.topic}")
    private String notificationsTopic;

    private final SimpMessagingTemplate messagingTemplate;

    public MessageListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "notifications.groups", groupId = "kafka-orderup")
    public void listenGroup(Message message) {
        System.out.println("listening: " + message.toString());
        messagingTemplate.convertAndSend("/topic/group/" + message.receiver(), message);
    }

    @KafkaListener(topics = "notifications.users", groupId = "kafka-orderup")
    public void listen(Message message) {
        System.out.println("listening: " + message.toString());
        messagingTemplate.convertAndSend("/topic/user/" + message.receiver(), message);
    }
}
