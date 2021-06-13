package com.agirpourtous.core.api;

import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.api.services.*;
import com.agirpourtous.core.models.User;
import org.eclipse.jetty.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.retry.Repeat;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import static java.nio.file.Files.*;

public class APIClient {
    private static final Logger logger = LoggerFactory.getLogger(APIClient.class.getName());

    private final WebClient client;
    private final HttpClient httpClient;
    private final UserService userService;
    private final AuthService authService;
    private final ProjectService projectService;
    private final TicketService ticketService;
    private final CommentService commentService;
    private final boolean isActive = true;
    private boolean stayConnected;
    private User user;

    public APIClient() {
        httpClient = new HttpClient();
        ClientHttpConnector connector = new JettyClientHttpConnector(httpClient);
        client = WebClient.builder()
                .baseUrl("http://localhost:4500/org-app")
                .clientConnector(connector)
                .build();
        try {
            httpClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService = new UserService(this);
        authService = new AuthService(this);
        projectService = new ProjectService(this);
        ticketService = new TicketService(this);
        commentService = new CommentService(this);
        loadCookies();
        try {
            userService.getCurrentUser()
                    .doOnSuccess(this::setUser)
                    .block();
        } catch (Exception ignored) {
        }
        refreshUser();
    }

    public void refreshUser() {
        userService.getCurrentUser()
                .repeatWhen(Repeat.onlyIf(repeatContext -> isActive)
                        .fixedBackoff(Duration.ofSeconds(10)))
                .subscribe(this::setUser);
    }

    public Mono<User> connect(LoginRequest loginRequest) {
        logger.info("Logging in to the api");
        return authService.login(loginRequest)
                .doOnSuccess(user -> {
                    this.user = user;
                    saveCookies();
                });
    }

    public void logout() {
        if (this.user != null) {
            logger.info("Logging out from the api");
            authService.logout().block();
            this.user = null;
            try {
                deleteIfExists(Path.of("cookie.bin"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (!stayConnected) {
            logout();
            try {
                deleteIfExists(Path.of("cookie.bin"));
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private void saveCookies() {
        try {
            deleteIfExists(Path.of("cookie.bin"));
            for (HttpCookie httpCookie : httpClient.getCookieStore().getCookies()) {
                writeString(Path.of("cookie.bin"), httpCookie.toString() + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCookies() {
        try {
            if (notExists(Path.of("cookie.bin"))) {
                return;
            }
            stayConnected = true;
            List<String> cookies = readAllLines(Path.of("cookie.bin"));
            for (String cookie : cookies) {
                for (HttpCookie httpCookie : HttpCookie.parse(cookie)) {
                    httpClient.getCookieStore().add(URI.create("http://localhost:4500/"), httpCookie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
