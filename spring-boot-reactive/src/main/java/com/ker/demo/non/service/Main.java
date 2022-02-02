package com.ker.demo.non.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {
        creatingMonoAndFlux();
        subscribe();
        generateSequenceSynchronousSink();
    }

    private static void generateSequenceSynchronousSink() {
        // The simplest form of programmatic creation of a Flux is through the generate method, which takes a generator function.
        // This is for synchronous and one-by-one emissions, meaning that the sink is a SynchronousSink and that its next() method can only be called at most once per callback invocation.
        Flux<String> flux = Flux.generate(
                () -> 0,        // immutable state info
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);    // next item in sequence
                    if (state == 10) sink.complete();   // mark stream as complete
                    return state + 1;       // return updated state
                });

        flux.subscribe(System.out::println);

        flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();   // mutate the state here.
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;   // return the same instance as the new state.
                });

        flux.subscribe(System.out::println);
    }

    private static void subscribe() {
        // consumer
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(System.out::println);

        // consumer and error handler
        ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(System.out::println,
                error -> System.err.println("Error: " + error));

        // consumer, error handler and completion consumer
        ints = Flux.range(1, 4);
        ints.subscribe(System.out::println,
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
