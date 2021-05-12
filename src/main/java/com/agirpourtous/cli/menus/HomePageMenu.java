package com.agirpourtous.cli.menus;

import com.agirpourtous.core.api.APIClient;

public class HomePageMenu extends Menu{

    public HomePageMenu(APIClient client){
        super( "Home Page Menu");

        addAction(new Action("Project Menu") {
            @Override
            public void execute() {
                new ProjectMenu(client);
            }
        });

        addAction(new Action("User Menu") {
            @Override
            public void execute() {
                new UserMenu(client);
            }
        });

        addAction(new Action("Ticket Menu") {
            @Override
            public void execute() {
                new TicketMenu(client);
            }
        });

        addAction(new Action("Comment Menu") {
            @Override
            public void execute() {
                new CommentMenu(client);
            }
        });

        addAction(new Action("Disconnect") {
            @Override
            public void execute() {
                client.close();
            }
        });
        start();
    }
}
