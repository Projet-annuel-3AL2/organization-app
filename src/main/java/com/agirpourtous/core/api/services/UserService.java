package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;


public class UserService extends Service<User>{

    public UserService(APIClient client) {
        super(client,"/user/", User.class);
    }

    public Flux<User> findAll(Function<ClientResponse, Mono<? extends Throwable>> function){
        return client.getClient().get()
                .uri(baseRoute)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .onStatus(HttpStatus.UNAUTHORIZED::equals, function)
                .bodyToFlux(type);
    }

    public Mono<User> findById(String id,  Function<ClientResponse, Mono<? extends Throwable>> function){
        return client.getClient().get()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .onStatus(HttpStatus.UNAUTHORIZED::equals, function)
                .bodyToMono(type);
    }

    public Mono<User> create(User e, Function<ClientResponse, Mono<? extends Throwable>> function){
        return client.getClient().post()
                .uri(baseRoute)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .body(Mono.just(e), type)
                .retrieve()
                .onStatus(HttpStatus.UNAUTHORIZED::equals, function)
                .bodyToMono(type);
    }

    public Mono<User> update(User user,  Function<ClientResponse, Mono<? extends Throwable>> function){
        return client.getClient().put()
                .uri(baseRoute + user.getId())
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .body(Mono.just(user), type)
                .retrieve()
                .onStatus(HttpStatus.UNAUTHORIZED::equals, function)
                .bodyToMono(type);
    }

    public Mono<Void> delete(String id,  Function<ClientResponse, Mono<? extends Throwable>> function){
        return client.getClient().delete()
                .uri(baseRoute +id)
                .accept(MediaType.APPLICATION_JSON)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .onStatus(HttpStatus.UNAUTHORIZED::equals, function)
                .bodyToMono(Void.class);
    }
}
