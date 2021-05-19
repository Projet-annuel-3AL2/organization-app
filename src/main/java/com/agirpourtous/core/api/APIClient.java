package com.agirpourtous.core.api;

import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.api.services.*;
import com.agirpourtous.core.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class APIClient {
    private static final Logger logger = LoggerFactory.getLogger(APIClient.class.getName());

    private final WebClient client;
    private final UserService userService;
    private final AuthService authService;
    private final ProjectService projectService;
    private final TicketService ticketService;
    private final CommentService commentService;
    private boolean stayConnected;
    private User user;

    public APIClient() {
        HttpClient httpClient = new HttpClient();
        ClientHttpConnector connector = new JettyClientHttpConnector(httpClient);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        client = WebClient.builder()
                .baseUrl("http://localhost:4500/org-app")
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON));
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON));
                })
                .clientConnector(connector)
                .build();
        userService = new UserService(this);
        authService = new AuthService(this);
        projectService = new ProjectService(this);
        ticketService = new TicketService(this);
        commentService = new CommentService(this);
        user = null;
    }

    public Mono<User> connect(LoginRequest loginRequest) {
        logger.info("Logging in to the api");
        return authService.login(loginRequest)
                .doOnSuccess(user -> this.user = user);
    }

    public void logout() {
        if (this.user != null) {
            logger.info("Logging out from the api");
            authService.logout().block();
            this.user = null;
        }
    }

    public void close() {
        if (!stayConnected) {
            logout();
        }
        System.exit(0);
    }

    public WebClient getClient() {
        return client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
