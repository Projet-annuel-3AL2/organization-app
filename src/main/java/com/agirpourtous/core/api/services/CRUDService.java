package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Entity;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class CRUDService<T extends Entity> {
    private final Class<T> type;
    private final APIClient client;
    private final String baseRoute;

    public CRUDService(APIClient client, String baseRoute, Class<T> type) {
        this.client = client;
        this.baseRoute = baseRoute;
        this.type = type;
    }

    public Flux<T> findAll(Class<T> B){
        return client.getClient().get()
                .uri(baseRoute)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToFlux(type);
    }

    public Mono<T> findById(String id){
        return client.getClient().get()
                .uri(baseRoute + id)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                /*.onStatus(HttpStatus.NOT_FOUND::equals,
                        clientResponse -> response -> Mono.error(new ServiceException("Method not allowed. Please check the URL.", response.statusCode().value())))
     */           .bodyToMono(type);
    }

    public Mono<T> create(T e){
        return client.getClient().post()
                .uri(baseRoute)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .body(Mono.just(e), type)
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<T> update(T e){
        return client.getClient().put()
                .uri(baseRoute + e.getId())
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .body(Mono.just(e), type)
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<Void> delete(String id){
        return client.getClient().delete()
                .uri(baseRoute +id)
                .cookies(cookies -> cookies.addAll(client.getCookies()))
                .retrieve()
                .bodyToMono(Void.class);
    }
}
