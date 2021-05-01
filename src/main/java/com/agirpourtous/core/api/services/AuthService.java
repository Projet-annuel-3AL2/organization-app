package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.util.Collections;

public class AuthService extends Service<User> {
    public AuthService(APIClient client) {
        super(client, "/auth/", User.class);
    }

    public Mono<User> login(LoginRequest loginRequest) {
        return client.getClient().post()
                .uri("/auth/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .body(Mono.just(loginRequest), LoginRequest.class)
                .retrieve()
                .onStatus(HttpStatus::is2xxSuccessful, r -> {
                    for (String key : r.cookies().keySet()) {
                        client.getCookies().put(key, Collections.singletonList(r.cookies().get(key).get(0).getValue()));
                    }
                    return Mono.empty();
                })
                .bodyToMono(User.class);
    }

    public void logout() {
        client.getClient().post()
                .uri("/auth/logout")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .onStatus(HttpStatus::is2xxSuccessful, r -> {
                    for (String key : r.cookies().keySet()) {
                        client.getCookies().put(key, Collections.singletonList(r.cookies().get(key).get(0).getValue()));
                    }
                    return Mono.empty();
                });
    }

}
