package com.agirpourtous.cli.menus.forms;

import com.agirpourtous.core.api.requests.AddTicketRequest;

public class AddTicketForm extends Form {
    @Override
    public AddTicketRequest askEntries() {
        AddTicketRequest addTicketRequest = new AddTicketRequest();
        addTicketRequest.setTitle(stringField("Veuillez entrer un titre"));
        addTicketRequest.setDescription(stringField("Veuillez entrer une description"));
        addTicketRequest.setPriority(numberField("Veuillez entrer la priorité du ticket", 0, 10));
        addTicketRequest.setEstimatedDuration(numberField("Veuillez entrer une estimation de la duré", 0, 14));
        return addTicketRequest;
    }
}
