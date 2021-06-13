package com.agirpourtous.cli.menus.forms;

import com.agirpourtous.core.api.requests.AddCommentRequest;

public class AddCommentForm extends Form {
    @Override
    public AddCommentRequest askEntries() {
        AddCommentRequest addCommentRequest = new AddCommentRequest();
        addCommentRequest.setText(stringField("Veuillez saisir votre commentaire"));
        return addCommentRequest;
    }
}
