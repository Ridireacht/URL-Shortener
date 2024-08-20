package com.sample.url_shortener.service;

public interface DatabaseLookupService {

    String findUrlByHash(String hash);

    String findHashByUrl(String url);
}
