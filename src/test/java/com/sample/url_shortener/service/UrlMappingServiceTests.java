package com.sample.url_shortener.service;

import com.sample.url_shortener.entity.UrlMapping;
import com.sample.url_shortener.repository.UrlMappingRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

@SpringBootTest
@Transactional
public class UrlMappingServiceTests {

    @Autowired
    private UrlMappingService urlMappingService;
    @Autowired
    private UrlMappingRepository urlMappingRepository;
    @Autowired
    private CacheManager cacheManager;

    @AfterEach
    void clearCache() {
        cacheManager.getCache("urls").clear();
        cacheManager.getCache("hashes").clear();
    }


    @Test
    void saveUrl_NewUrl() {
        String url = "https://example.com";

        String hash = urlMappingService.saveUrl(url);

        var queryResult = urlMappingRepository.findByUrl(url);

        assert(queryResult.isPresent());
        assert(queryResult.get().getHash().equals(hash));
    }

    @Test
    void saveUrl_ExistingUrl() {
        String hash = "aK3x5";
        String url = "https://example.com";

        urlMappingRepository.save(new UrlMapping(hash, url));

        assert(urlMappingService.saveUrl(url).equals(hash));
    }
}
