package com.sample.url_shortener.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Getter
@Setter
public class SaveRequestDTO {

    String url;

    static public boolean isURL(String input) {
        try {
            new URL(input).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
}
