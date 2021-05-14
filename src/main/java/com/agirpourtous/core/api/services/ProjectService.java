package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.AddProjectRequest;
import com.agirpourtous.core.api.requests.UsersManagementRequest;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProjectService extends Service<Project> {
    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class.getName());

    public ProjectService(APIClient client) {
        super(client, "/project/", Project.class);
    }

    public Mono<Project> create(AddProjectRequest addProjectRequest) {
        return client.getClient().post()
                .uri(baseRoute)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(addProjectRequest), type)
                .retrieve()
                .bodyToMono(type);
    }

    public Mono<Project> update(String id, Project project) {
        return client.getClient().put()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(project), type)
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

    public Flux<Project> findAll() {
        return client.getClient().get()
                .uri(baseRoute)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(type);
    }

    public Mono<Project> findById(String id) {
        return client.getClient().get()
                .uri(baseRoute + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(type);
    }

    public Flux<User> getMembers(String id) {
        return client.getClient().get()
                .uri(baseRoute + "/{id}/members/", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(User.class);
    }

    public Flux<User> getAdmins(String id) {
        return client.getClient().get()
                .uri(baseRoute + "/{id}/admins/", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(User.class);
    }

    public Mono<Void> removeAdmin(String projectId, String userId) {
        return client.getClient().delete()
                .uri(baseRoute + "/{projectId}/admin/{userId}", projectId, userId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> removeMember(String projectId, String userId) {
        return client.getClient().delete()
                .uri(baseRoute + "/{projectId}/member/{userId}", projectId, userId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> addMembers(String projectId, UsersManagementRequest usersManagementRequest) {
        return client.getClient().put()
                .uri(baseRoute + "/{projectId}/member/{userId}", projectId, usersManagementRequest)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> removeMembers(String projectId, UsersManagementRequest usersManagementRequest) {
        return client.getClient().delete()
                .uri(baseRoute + "/{projectId}/member/{userId}", projectId, usersManagementRequest)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Flux<Ticket> getTickets(String id) {
        return client.getClient().get()
                .uri(baseRoute + "/{id}/tickets/", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Ticket.class);
    }

    public Mono<Ticket> addTicket(String id, Ticket ticket) {
        return client.getClient().post()
                .uri(baseRoute + "/{id}/ticket", id)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(ticket), type)
                .retrieve()
                .bodyToMono(Ticket.class);
    }
}
