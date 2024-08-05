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

    @GetMapping("/r/{hashUrl}")
    public String getRedirect(@PathVariable String hashUrl) {
        String url = databaseLookupService.findUrlByHash(hashUrl);

        if (url != null) {
            return "redirect:" + url;
        } else {
            return "404";
        }
    }
}
