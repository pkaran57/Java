package com.ker.demo.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @GetMapping("/echo/{toEcho}")
    public Mono<String> getUser(@PathVariable String toEcho) {
        return Mono.just("Echoing " + toEcho);
    }

    @PostMapping("/echoBody")
    public Mono<String> getUser(@RequestBody JsonNode echoBody) {
        return Mono.just("Echoing " + echoBody.toString());
    }
}