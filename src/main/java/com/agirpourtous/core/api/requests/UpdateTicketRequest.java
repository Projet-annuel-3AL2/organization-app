package com.agirpourtous.core.api.requests;

import com.agirpourtous.core.models.TicketStatus;

public class UpdateTicketRequest {
    private TicketStatus status;

    public UpdateTicketRequest(TicketStatus status) {
        this.status = status;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
