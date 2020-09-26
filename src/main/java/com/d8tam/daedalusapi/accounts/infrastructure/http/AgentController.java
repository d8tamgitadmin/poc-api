package com.d8tam.daedalusapi.accounts.infrastructure.http;

import com.d8tam.daedalusapi.accounts.domain.models.Agent;
import com.d8tam.daedalusapi.accounts.domain.services.AgentService;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/agents")
public class AgentController {

    @Autowired
    private AgentService service;

    @Autowired
    private Executor executor;

   @GetMapping(value = "/")
   public CompletableFuture<ResponseEntity<?>> getAgents(){
       return service.FindAllAgents().thenApplyAsync(ResponseEntity::ok,executor);
   }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<?>> getAgentsByIdAsync(@PathVariable @NotNull Long id){
        return service.FindById(id).thenApplyAsync(ResponseEntity::ok,executor);
    }

    @PostMapping(value = "/")
    public CompletableFuture<ResponseEntity<?>> postAgentAsync(@RequestBody Agent agentDto) {
        return service.CreateAgent(agentDto).thenApplyAsync(r ->ResponseEntity.created(URI.create("/api/v1/agents"+r.id)).body(r));
    }

    @PutMapping(value = "/")
    public CompletableFuture<ResponseEntity<?>> updateAgentAsync(@RequestBody Agent agentDto){
        return service.CreateAgent(agentDto).thenApplyAsync(ResponseEntity::ok);
    }

    @DeleteMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<?>> deleteAgentAsync(@PathVariable @NotNull Long id){
        return service.DeleteAgentById(id).thenApplyAsync(ResponseEntity::ok);
    }





}
