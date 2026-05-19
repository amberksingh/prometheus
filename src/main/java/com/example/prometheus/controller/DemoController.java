package com.example.prometheus.controller;

import com.example.prometheus.service.ServiceClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/monitoring")
public class DemoController {

    String str = "main committed stash demo string";

    String stashStr = "stash scenario main string";

    String stashOne = "first stash change";

    String stashTwo = "second stash change";

    @Autowired
    ServiceClass service;

    int count = 0;

    //curl -X POST "http://localhost:8080/shorten?longUrl=https://google.com"

    @GetMapping("/message")
    public String message() {
        log.info("inside message() method - count={}", ++count);
        return "hello message";
    }

    @PostMapping("/shorten")
    public String shorten(@RequestParam String longUrl) {
        return service.shortenUrl(longUrl);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String longUrl = service.getLongUrl(shortCode);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }
}
