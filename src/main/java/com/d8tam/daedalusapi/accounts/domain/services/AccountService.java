package com.d8tam.daedalusapi.accounts.domain.services;


import com.d8tam.daedalusapi.accounts.domain.models.Account;
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
public class AccountService {

    @Autowired
    private AccountJPARepository repository;

    @Autowired
    private AgentJpaRepository agentJpaRepository;

    @Autowired
    public Executor executor;

    public CompletableFuture<List<Account>> GetAllAccountsAsync(){
        return CompletableFuture.supplyAsync(()-> this.repository.findAll(),executor)
                .thenApplyAsync(accounts -> {
                    accounts.forEach(account -> {
                        account.setAgents(agentJpaRepository.findByAccountId(account.id));
                    });
                    return accounts;
                },executor);
    }

    public CompletableFuture<Optional<Account>> GetAccountById(Long id){
        return CompletableFuture.supplyAsync(()-> this.repository.findById(id),executor)
                .thenApplyAsync(r -> {
                    r.ifPresent(a ->a.setAgents(agentJpaRepository.findByAccountId(a.id)));
                    return r;
                },executor);
    }

    public CompletableFuture<Account> CreateAccount(Account account) {
        return CompletableFuture.supplyAsync(()  -> this.repository.save(account),executor);
    }

    public CompletableFuture<Account> UpdateAccount(Account account, Long id){
        return CompletableFuture.supplyAsync(() -> this.repository.save(account),executor);
    }

    public CompletableFuture<Void> DeleteAccountById(Long id){
        return CompletableFuture.supplyAsync(() -> this.repository.findById(id),executor)
                .thenAcceptAsync(result -> result.ifPresent(this.repository::delete),executor);
    }
}
