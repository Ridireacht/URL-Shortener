package com.sample.url_shortener.service;

public interface DatabaseLookupService {

    String findUrlByKey(String hash);

    String findKeyByUrl(String url);
}
