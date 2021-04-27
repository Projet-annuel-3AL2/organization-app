package com.agirpourtous.core.api;


import com.agirpourtous.core.api.services.CRUDService;
import com.agirpourtous.core.models.User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

public class APIClient {
    private final WebClient client;
    MultiValueMap<String, String> savedCookies = new LinkedMultiValueMap<>();
    private final CRUDService<User> userCRUDService;


    public APIClient() {
         client = WebClient.create("http://localhost:3000/");
         userCRUDService = new CRUDService<>(this,"/user/", User.class);
    }

    public void connect(String username, String password) {
        String json = "{username:'" + username + "', password:'" + password + "'}";
    }

    public WebClient getClient() {
        return client;
    }

    public MultiValueMap<String, String> getCookies() {
        return savedCookies;
    }
}
