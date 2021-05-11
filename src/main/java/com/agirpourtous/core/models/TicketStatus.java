package com.agirpourtous.core.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public enum TicketStatus {
    TODO, OPEN, CLOSED
}
