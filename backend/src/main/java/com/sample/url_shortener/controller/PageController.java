package com.sample.url_shortener.controller;

import com.sample.url_shortener.service.DatabaseLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final DatabaseLookupService databaseLookupService;


    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/r/{hash}")
    public String getRedirect(@PathVariable String hash) {
        String url = databaseLookupService.findUrlByHash(hash);

        if (url != null) {
            return "redirect:" + url;
        } else {
            return "error/404";
        }
    }
}
