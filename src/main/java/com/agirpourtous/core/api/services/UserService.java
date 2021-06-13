package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.AddUserRequest;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class UserService extends Service<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    public UserService(APIClient client) {
        super(client, "/user/", User.class);
    }

    public Flux<User> findAll() {
        return client.getClient().get()
                .uri(baseRoute)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(type);
    }

    public Mono<User> findById(String id) {
        return client.getClient().get()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<User> getCurrentUser() {
        return client.getClient().get()
                .uri(baseRoute + "/me")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<User> create(AddUserRequest addUserRequest) {
        return client.getClient().post()
                .uri(baseRoute)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(addUserRequest), type)
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<User> setAdmin(User user) {
        return client.getClient().put()
                .uri(baseRoute + "/set-admin/" + user.getId())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(type);
    }

    public Flux<Project> getProjects() {
        return client.getClient().get()
                .uri(baseRoute + "/{id}/projects", client.getUser().getId())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Project.class);
    }

    public Mono<User> update(String id, User user) {
        return client.getClient().put()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), type)
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<Void> delete(String id) {
        return client.getClient().delete()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
