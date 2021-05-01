package com.agirpourtous.core.api;

import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.api.services.AuthService;
import com.agirpourtous.core.api.services.UserService;
import com.agirpourtous.core.models.User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

public class APIClient {
    private final WebClient client;
    private final MultiValueMap<String, String> savedCookies;
    private final UserService userService;
    private final AuthService authService;
    private User user;


    public APIClient() {
        savedCookies = new LinkedMultiValueMap<>();
        client = WebClient.create("http://localhost:4500/org-app");
        userService = new UserService(this);
        authService = new AuthService(this);
        user = null;
    }

    public boolean connect(LoginRequest loginRequest) {
        this.user = authService.login(loginRequest).block();
        return this.user != null;
    }

    public void logout() {
        if(this.user != null) {
            authService.logout().block();
            this.user = null;
        }
    }

    public void close() {
        logout();
    }

    public WebClient getClient() {
        return client;
    }

    public MultiValueMap<String, String> getCookies() {
        return savedCookies;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
