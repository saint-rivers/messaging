package com.saintrivers.messaging.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
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

    @PostMapping("/notifications/send/user")
    public ResponseEntity<Map<String, String>> sendMessageUser(@RequestBody Message body) {
        kafkaTemplate.send("notifications.users", body);

        var response = new HashMap<String, String>();
        response.put("message", "sent to user " + body.receiver());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/notifications/send/group")
    public ResponseEntity<Map<String, String>> sendMessageToGroup(@RequestBody Message body) {
        kafkaTemplate.send("notifications.groups", body);

        var response = new HashMap<String, String>();
        response.put("message", "sent to group " + body.receiver());
        return ResponseEntity.ok().body(response);
    }
}
