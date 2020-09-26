package com.d8tam.daedalusapi.accounts.infrastructure.async;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@Slf4j
public class AsyncRunner {

    private Executor executor;

    @Bean
    public Executor Executor(){
       if(executor != null){
           return executor;
       }
       this.executor =Executors.newFixedThreadPool(10);
       return executor;
    }
}