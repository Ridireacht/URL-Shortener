package com.sample.url_shortener.service;

public interface UrlMappingService {

    String saveUrl(String url);

    String findUrlByHash(String hash);
}
