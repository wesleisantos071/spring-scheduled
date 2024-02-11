package com.theweslei.samples.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerClass {
    private final TaskService taskService;

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Scheduled(fixedDelay = 3000)
    void runScheduled() {
        Future<Void> future = taskService.executeSomething();
        ScheduledFuture<?> scheduledFuture = executorService.schedule(() -> {
            log.error("Task timed out. Cancelling...");
            future.cancel(true);
        }, 1, TimeUnit.SECONDS);

        try {
            future.get();
        } catch (CancellationException e) {
            log.error("Task timed out.");
        } catch (InterruptedException | ExecutionException e) {
            log.error("Task execution failed", e);
        } finally {
            scheduledFuture.cancel(true);
        }
    }
}
