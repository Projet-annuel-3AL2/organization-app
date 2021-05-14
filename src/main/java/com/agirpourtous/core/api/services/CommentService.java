package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CommentService extends Service<Comment> {
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class.getName());

    public CommentService(APIClient client) {
        super(client, "/comment/", Comment.class);
    }

    public Flux<Comment> findAll() {
        return client.getClient().get()
                .uri(baseRoute)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(type);
    }

    public Mono<Comment> findById(String id) {
        return client.getClient().get()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<Comment> update(String id, Comment comment) {
        return client.getClient().put()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(comment), type)
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
