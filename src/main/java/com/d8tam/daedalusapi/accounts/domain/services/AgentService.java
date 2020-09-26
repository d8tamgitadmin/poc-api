package com.d8tam.daedalusapi.accounts.domain.services;

import com.d8tam.daedalusapi.accounts.domain.models.Agent;
import com.d8tam.daedalusapi.accounts.infrastructure.jpa.AccountJPARepository;
import com.d8tam.daedalusapi.accounts.infrastructure.jpa.AgentJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@Slf4j
public class AgentService {

    @Autowired
    private AgentJpaRepository repository;

    @Autowired
    private Executor executor;

    public CompletableFuture<Optional<Agent>> FindById(Long id){
        return CompletableFuture.supplyAsync(() -> this.repository.findById(id),executor);
    }

    public CompletableFuture<List<Agent>> FindAllAgents(){
       return CompletableFuture.supplyAsync(this.repository::findAll,executor);
    }

    public CompletableFuture<Agent> CreateAgent(Agent agent){
        return CompletableFuture.supplyAsync(() -> this.repository.save(agent),executor);
    }

    public CompletableFuture<Void> DeleteAgentById(Long id) {
        return CompletableFuture.supplyAsync(() -> this.repository.findById(id),executor)
                .thenAcceptAsync(result -> result.ifPresent(this.repository::delete),executor);
    }
}
