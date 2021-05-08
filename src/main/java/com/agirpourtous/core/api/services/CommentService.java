package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.User;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CommentService extends Service<Comment>{
    public CommentService(APIClient client) {
        super(client, "/comment/", Comment.class);
    }

    public Flux<Comment> findAll() {
        return client.getClient().get()
                .uri(baseRoute)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToFlux(type);
    }

    public Mono<Comment> findById(String id) {
        return client.getClient().get()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<Comment> create(User user) {
        return client.getClient().post()
                .uri(baseRoute)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .body(Mono.just(user), type)
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<Comment> update(String id, User user) {
        return client.getClient().put()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), type)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToMono(type);
    }
}
