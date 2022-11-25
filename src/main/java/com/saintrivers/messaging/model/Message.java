package com.saintrivers.messaging.model;

import java.util.UUID;

public record Message(
                UUID sender,
                UUID receiver,
                String payload) {
}
