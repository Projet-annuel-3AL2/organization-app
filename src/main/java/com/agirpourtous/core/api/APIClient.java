package com.agirpourtous.core.api;

import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.api.services.UserService;
import com.agirpourtous.core.models.User;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class APIClient {
    private User user;
    private final WebClient client;
    MultiValueMap<String, String> savedCookies = new LinkedMultiValueMap<>();
    private final UserService userService;


    public APIClient() {
         client = WebClient.create("http://localhost:4500/org-app");
         userService = new UserService(this);
    }

    public Mono<User> connect(LoginRequest loginRequest) {
        Mono<User> userCb = client.post()
                .uri("/auth/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(savedCookies))
                .body(Mono.just(loginRequest), LoginRequest.class)
                .retrieve()
                .bodyToMono(User.class);
        userCb.subscribe(user-> this.user = user);
        return userCb;
    }

    public WebClient getClient() {
        return client;
    }

    public MultiValueMap<String, String> getCookies() {
        return savedCookies;
    }
}
