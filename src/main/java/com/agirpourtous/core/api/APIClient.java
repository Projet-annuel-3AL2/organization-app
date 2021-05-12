package com.agirpourtous.core.api;

import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.api.services.*;
import com.agirpourtous.core.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

public class APIClient {
    private static final Logger logger = LoggerFactory.getLogger(APIClient.class.getName());

    private final WebClient client;
    private final MultiValueMap<String, String> savedCookies;
    private final UserService userService;
    private final AuthService authService;
    private final ProjectService projectService;
    private final TicketService ticketService;
    private final CommentService commentService;
    private boolean stayConnected;
    private User user;


    public APIClient() {
        savedCookies = new LinkedMultiValueMap<>();
        client = WebClient.builder()
                .baseUrl("http://localhost:4500/org-app")
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().compress(true)))
                .build();
        userService = new UserService(this);
        authService = new AuthService(this);
        projectService = new ProjectService(this);
        ticketService = new TicketService(this);
        commentService = new CommentService(this);
        user = null;
    }

    public boolean connect(LoginRequest loginRequest) {
        this.user = authService.login(loginRequest).block();
        return this.user != null;
    }

    public void logout() {
        if (this.user != null) {
            authService.logout().block();
            this.user = null;
        }
    }

    public void close() {
        if (!stayConnected) {
            logout();
        }
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

    public MultiValueMap<String, String> getSavedCookies() {
        return savedCookies;
    }

    public UserService getUserService() {
        return userService;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public boolean isStayConnected() {
        return stayConnected;
    }

    public void setStayConnected(boolean stayConnected) {
        this.stayConnected = stayConnected;
    }
}
