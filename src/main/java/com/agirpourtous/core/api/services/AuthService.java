package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

public class AuthService extends Service<User> {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class.getName());

    public AuthService(APIClient client) {
        super(client, "/auth/", User.class);
    }

    public Mono<User> login(LoginRequest loginRequest) {
        return client.getClient().post()
                .uri("/auth/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginRequest), LoginRequest.class)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<Void> logout() {
        return client.getClient().delete()
                .uri("/auth/logout")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
