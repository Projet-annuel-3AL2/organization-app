package com.agirpourtous.core.api;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIConnexion {
    private String token;
    private boolean connected;

    public APIConnexion(String token){
        this.connected = false;
        this.token = token;
    }
    public APIConnexion(){
        this.connected = false;
    }

    public void connect(String username, String password) throws IOException {
        String json = "{username:'"+username+"', password:'"+password+"'}";
        token = postData(json, new URL("http://localhost/auth/login"));
    }

    public String postData(String data, URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        byte[] input = data.getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }
        return response.toString();
    }
}
