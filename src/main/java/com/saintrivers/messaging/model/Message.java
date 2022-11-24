package com.saintrivers.messaging.model;

import java.util.UUID;

public record Message(
                UUID id,
                String payload) {
}
