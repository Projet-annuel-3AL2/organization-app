package com.agirpourtous;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.gui.GUILauncher;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = generateCLIOptions();
        try {
            CommandLine line = parser.parse(options, args);
            APIClient client = new APIClient();
            if (line.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("help", options);
                System.exit(0);
            }
            if (line.hasOption("username") && line.hasOption("password")) {
                client.connect(line.getOptionValue("username"), line.getOptionValue("password"));
            }
            if (!line.hasOption("console")) {
                GUILauncher.setClient(client);
                GUILauncher.launch(GUILauncher.class);
            } else {
                System.out.println("Start in CLI mode");
            }
        } catch (ParseException exp) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(exp.getMessage(), options);
        }
    }

    private static Options generateCLIOptions() {
        Options options = new Options();
        options.addOption("h", "help", false, "Display available options details");
        options.addOption("c", "console", false, "Launches the organization-app in a CLI mode");
        options.addOption("u", "username", true, "Username to connect to the organization-app");
        options.addOption("p", "password", true, "Password to connect to the organization-app");
        return options;
    }
}