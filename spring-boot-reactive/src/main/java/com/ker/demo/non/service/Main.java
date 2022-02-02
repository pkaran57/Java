package com.ker.demo.non.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        creatingMonoAndFlux();
        subscribe();
    }

    private static void subscribe() {
        // consumer
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(i -> System.out.println(i));

        // consumer and error handler
        ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error: " + error));

        // consumer, error handler and completion consumer
        ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));

        Mono.just("test").subscribe(str -> System.out.println("From mono subscriber - " + str));
    }

    private static void creatingMonoAndFlux() {
        // Mono, an Asynchronous 0-1 Result
        Mono<Object> emptyMono = Mono.empty();
        Mono<String> monoFromJust = Mono.just("test");

        // Flux, an Asynchronous Sequence of 0-N Items
        Flux<String> empty = Flux.empty();
        Flux<String> fromJust = Flux.just("foo", "bar", "foobar");
        Flux<String> fromIterable = Flux.fromIterable(List.of("foo", "bar", "foobar"));
        Flux<Integer> fromRange = Flux.range(5, 3);
    }
}
