package com.example.leaderboard.config;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.*;
import java.util.function.Consumer;

@Component
public class TeamCreatedEventPublisher implements
        ApplicationListener<TeamCreatedEvent>, // <1>
        Consumer<FluxSink<TeamCreatedEvent>> { //<2>

    private final ExecutorService executor;

    private final BlockingQueue<TeamCreatedEvent> queue =
            new LinkedBlockingQueue<>(); // <3>

    TeamCreatedEventPublisher() {
        this.executor = Executors.newSingleThreadExecutor();
    }

    // <4>
    @Override
    public void onApplicationEvent(TeamCreatedEvent event) {
        this.queue.offer(event);
    }

    @Override
    public void accept(FluxSink<TeamCreatedEvent> sink) {
        this.executor.execute(() -> {
            while (true)
                try {
                    TeamCreatedEvent event = queue.take(); // <5>
                    sink.next(event); // <6>
                }
                catch (InterruptedException e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }
        });
    }
}
