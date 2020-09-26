package com.d8tam.daedalusapi.accounts.infrastructure.http;


import com.d8tam.daedalusapi.accounts.domain.models.Account;
import com.d8tam.daedalusapi.accounts.domain.services.AccountService;
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
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private Executor executor;

    @GetMapping(value = "/")
    public CompletableFuture<ResponseEntity<?>> getAccountsAsync(){
        return accountService.GetAllAccountsAsync()
                .thenApplyAsync(r -> ResponseEntity.ok(r),executor);
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<?>> getAccountByIdAsync(@PathVariable @NotNull Long id){
       return accountService.GetAccountById(id)
               .thenApplyAsync(r -> ResponseEntity.ok(r),executor);
    }

    @PostMapping(value = "/")
    public CompletableFuture<ResponseEntity<?>> postAccountAsync(@RequestBody Account accountDto) {
        return accountService.CreateAccount(accountDto)
                .thenApplyAsync(r -> ResponseEntity.created(URI.create("/api/v1/accounts/"+r.id)).body(r),executor);
    }

    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<?>> putAccountAsync(@RequestBody Account accountDto) {
        return accountService.UpdateAccount(accountDto,accountDto.id)
                .thenApplyAsync(r -> ResponseEntity.status(HttpStatus.ACCEPTED).body(r),executor);
    }

    @DeleteMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<?>> deleteAccountByIdAsync(@PathVariable @NotNull Long id){
        return accountService.DeleteAccountById(id)
                .thenApplyAsync(r -> ResponseEntity.status(HttpStatus.ACCEPTED).build());
    }

}
