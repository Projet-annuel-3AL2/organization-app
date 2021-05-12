package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.controller.CommentController;
import com.agirpourtous.core.api.APIClient;

public class CommentMenu  extends Menu{

    private CommentController commentController = new CommentController();

    public CommentMenu(APIClient client) {
        super("Comment Menu");

        addAction(new Action("Find all Comment") {
            @Override
            public void execute() {
                commentController.getAllComment(client);
            }
        });

        addAction(new Action("Find Comment by Id") {
            @Override
            public void execute() {
                commentController.findById(client);
            }
        });

        addAction(new Action("Update a Comment with id Comment") {
            @Override
            public void execute() {
                commentController.updateComment(client);
            }
        });

        addAction(new Action("Delete a comment with Id") {
            @Override
            public void execute() {
                commentController.deleteComment(client);
            }
        });



        start();
    }
}
