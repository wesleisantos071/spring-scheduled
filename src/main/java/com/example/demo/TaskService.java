package com.example.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Service
public class TaskService {
    @Async
    Future<Void> executeSomething() {
        try {
            Thread.currentThread().setName(UUID.randomUUID().toString());
            log.info("im starting");
            while(true){
                log.info("im running ...");
                Thread.sleep(400);
            }
        } catch (InterruptedException e) {
            log.error("ops, someone killed me");
            Thread.currentThread().interrupt();
        }
        log.info("im finished");
        return CompletableFuture.completedFuture(null);
    }
}
