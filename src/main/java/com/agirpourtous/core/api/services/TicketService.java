package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.AddCommentRequest;
import com.agirpourtous.core.api.requests.AddTicketRequest;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TicketService extends Service<Ticket> {
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class.getName());

    public TicketService(APIClient client) {
        super(client, "/ticket/", Ticket.class);
    }

    public Flux<Ticket> findAll() {
        return client.getClient().get()
                .uri(baseRoute)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToFlux(type);
    }

    public Mono<Ticket> findById(String id) {
        return client.getClient().get()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<Void> delete(String id) {
        return client.getClient().delete()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Ticket> update(String id, AddTicketRequest addTicketRequest) {
        return client.getClient().put()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(addTicketRequest), type)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<Ticket> setAssignee(String ticketId, String userId) {
        return client.getClient().put()
                .uri(baseRoute + "/{ticketId}/assignee/{userId}", ticketId, userId)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToMono(type);
    }

    public Flux<Comment> getComments(String id) {
        return client.getClient().get()
                .uri(baseRoute + "{id}/comments", id)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToFlux(Comment.class);
    }

    public Mono<Comment> addComment(String id, AddCommentRequest addCommentRequest) {
        return client.getClient().post()
                .uri(baseRoute + "{id}/comment", id)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(addCommentRequest), type)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToMono(Comment.class);
    }
}
