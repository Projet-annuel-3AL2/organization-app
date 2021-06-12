package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.TicketStatus;

public class TicketStatusListMenu extends ListSelectionMenu {

    public TicketStatusListMenu(CLILauncher launcher) {
        super(launcher, "Selectioner un status");
    }

    @Override
    protected void loadEntityList() {
        for (TicketStatus status : TicketStatus.values()) {

            addAction(new ListAction(status.name()) {
                @Override
                public Object getEntity() {
                    return status;
                }
            });
        }
    }
}
