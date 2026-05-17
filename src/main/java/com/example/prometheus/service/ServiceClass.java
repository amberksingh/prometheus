package com.example.prometheus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class ServiceClass {

    private static Map<String, String> urlMap = new HashMap<>();

    public String shortenUrl(String longUrl) {

        log.info("input URL : {}", longUrl);
        Map<String, String> urlMap1 = getUrlMap();
        String shortCode = null;
        String shortenedURl = null;// = "http://localhost:8080/" + shortCode;
        if (urlMap1.containsValue(longUrl)) {
            log.info("Already existing URl");
            for (Map.Entry<String, String> entry : urlMap1.entrySet()) {
                if (entry.getValue().equals(longUrl)) {
                    shortCode = entry.getKey();
                    log.info("found shortCode = {}", shortCode);
                    break;
                }
            }
        } else {
            log.info("new URl..shortening the input URL.");
            shortCode = UUID.randomUUID()
                    .toString()
                    .substring(0, 6);
            log.info("shortCode : {}", shortCode);
            urlMap1.put(shortCode, longUrl);
        }
        shortenedURl = "http://localhost:8080/" + shortCode;
        log.info("shortenedURl : {}", shortenedURl);
        return shortenedURl;
//        String shortCode = UUID.randomUUID()
//                .toString()
//                .substring(0, 6);
//        log.info("shortCode : {}", shortCode);
//
//        String shortenedURl = "http://localhost:8080/" + shortCode;
//        log.info("shortenedURl : {}", shortenedURl);
//
//        if (!urlMap1.containsKey(shortCode)) {
//            urlMap1.put(shortCode, longUrl);
//            log.info("unique URl..");
//        } else {
//            log.info("already existing URl..");
//        }
//
//        return shortenedURl;
    }

    public String getLongUrl(String shortCode) {
        Map<String, String> urlMap2 = getUrlMap();
        if (urlMap2.containsKey(shortCode)) {
            log.info("Found the longUrl...");
            String longURl = urlMap2.get(shortCode);
            log.info("longURl : {}", longURl);
            return longURl;
        }
        log.warn("URL not found");
        return "invalid request/input";
    }

    static Map<String, String> getUrlMap() {
        return urlMap;
    }
}
