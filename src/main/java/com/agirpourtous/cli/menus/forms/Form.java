package com.agirpourtous.cli.menus.forms;

import com.agirpourtous.core.api.requests.Request;

import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Form {
    private final static Scanner SCANNER = new Scanner(System.in);

    public Form() {
    }

    public abstract Request askEntries();

    protected String stringField(String display) {
        String str = "";
        while (str.isBlank()) {
            System.out.println(display);
            str = SCANNER.nextLine();
        }
        return str;
    }

    protected String passwordField(String display) {
        String str = "";
        while (str.isBlank() && str.length() < 5) {
            System.out.println(display);
            str = SCANNER.nextLine();
        }
        return str;
    }

    protected String emailField(String display) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        String str = "";
        while (str.isBlank() || pat.matcher(str).matches()) {
            System.out.println(display);
            str = SCANNER.nextLine();
        }
        return str;
    }
}
