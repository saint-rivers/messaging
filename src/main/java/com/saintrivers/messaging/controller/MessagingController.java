package com.saintrivers.messaging.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saintrivers.messaging.model.Message;

@RestController
public class MessagingController {
    private final KafkaTemplate<Integer, Message> kafkaTemplate;

    public MessagingController(KafkaTemplate<Integer, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/notifications/send/user/{id}")
    public ResponseEntity<String> sendMessageUser(@PathVariable String id, @RequestBody Message body) {
        kafkaTemplate.send("notifications.users", body);
        return ResponseEntity.ok("sent to user " + id);
    }

    @PostMapping("/notifications/send/group/{id}")
    public ResponseEntity<String> sendMessageToGroup(@PathVariable String id, @RequestBody Message body) {
        kafkaTemplate.send("notifications.groups", body);
        return ResponseEntity.ok("sent to group " + id);
    }
}
