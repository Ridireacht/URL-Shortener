package com.sample.url_shortener.controller;

import com.sample.url_shortener.service.DatabaseLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final DatabaseLookupService databaseLookupService;


    @GetMapping("/r/{hash}")
    public String getRedirect(@PathVariable String hash) {
        String url = databaseLookupService.findUrlByHash(hash);

        if (url != null) {
            return "redirect:" + url;
        } else {
            return "redirect:http://localhost/error/404.html";
        }
    }
}
