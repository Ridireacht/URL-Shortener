package com.sample.url_shortener.controller;

import com.sample.url_shortener.dto.SaveRequestDTO;
import com.sample.url_shortener.service.UrlMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlMappingController {

    private final UrlMappingService urlMappingService;


    @PostMapping("/")
    public ResponseEntity<String> saveUrl(@RequestBody SaveRequestDTO hash) {
        return ResponseEntity.ok().body(urlMappingService.saveUrl(hash.getUrl()));
    }
}
