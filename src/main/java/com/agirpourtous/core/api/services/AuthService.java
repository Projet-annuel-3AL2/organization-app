package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.api.requests.ResetPasswordRequest;
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
                .uri(baseRoute + "/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginRequest), LoginRequest.class)
                .retrieve()
                .bodyToMono(User.class)
                .log();
    }

    public Mono<Void> logout() {
        return client.getClient().delete()
                .uri(baseRoute + "/logout")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class)
                .log();
    }

    public Mono<Void> resetPassword(ResetPasswordRequest resetPasswordRequest) {
        return client.getClient().post()
                .uri(baseRoute + "/reset-password/" + resetPasswordRequest.getToken())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(resetPasswordRequest), ResetPasswordRequest.class)
                .retrieve()
                .bodyToMono(Void.class)
                .log();
    }

    public Mono<Void> forgotPassword(String username) {
        return client.getClient().get()
                .uri(baseRoute + "/forgot-password/" + username)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class)
                .log();
    }
}
