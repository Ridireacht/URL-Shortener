package com.sample.url_shortener.service;

public interface DatabaseLookupService {

    String findUrlByKey(String key);

    String findKeyByUrl(String url);
}
