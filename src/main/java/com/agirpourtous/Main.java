package com.agirpourtous;

import com.agirpourtous.gui.GUILauncher;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = generateCLIOptions();
        try {
            CommandLine line = parser.parse(options, args);
            APIConnexion connexion = new APIConnexion();
            if(line.hasOption("help")){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("help", options);
                System.exit(0);
            }
            if(line.hasOption("username") && line.hasOption("password")) {
                connexion.connect(line.getOptionValue("username"), line.getOptionValue("password"));
            }
            if (!line.hasOption("console")) {
                System.out.println("Start in GUI mode");
                GUILauncher.run();
            } else {
                System.out.println("Start in CLI mode");
            }
        } catch (ParseException | IOException exp) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(exp.getMessage() ,options);
        }
    }

    private static Options generateCLIOptions() {
        Options options = new Options();
        options.addOption("h", "help", false, "Display available options details");
        options.addOption("c", "console", false, "Launches the organization-app in a CLI mode");
        options.addOption("u", "username", true,"Username to connect to the organization-app");
        options.addOption("p", "password", true,"Password to connect to the organization-app");
        return options;
    }
}