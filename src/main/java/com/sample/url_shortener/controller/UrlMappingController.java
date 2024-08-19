package com.sample.url_shortener.controller;

import com.sample.url_shortener.dto.SaveRequestDTO;
import com.sample.url_shortener.service.UrlMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequiredArgsConstructor
public class UrlMappingController {

    @Value("${APP_DOMAIN}")
    private String appDomain;

    private final UrlMappingService urlMappingService;


    @PostMapping("/")
    public ResponseEntity<String> saveUrl(@RequestBody SaveRequestDTO saveRequestDTO) {
        if (!isURL(saveRequestDTO.getUrl())) {
            return ResponseEntity.badRequest().body("Invalid URL format");
        }

        // Remove all unnecessary '/' at the end
        String url = saveRequestDTO.getUrl().replaceAll("/+$", "");

        if (url.length() > 1000) {
            return ResponseEntity.badRequest().body("URL too long (1000 characters at max)");
        }

        return ResponseEntity.ok().body(appDomain + "/r/" + urlMappingService.saveUrl(url));
    }

    
    private static boolean isURL(String input) {
        try {
            new URL(input).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
}
