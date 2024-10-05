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
public class DatabaseLookupServiceTests {

    @Autowired
    private DatabaseLookupService databaseLookupService;
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
    void findUrlByHash_Found() {
        String hash = "aK3x5";
        String url = "https://example.com";

        urlMappingRepository.save(new UrlMapping(hash, url));

        assert(databaseLookupService.findUrlByHash(hash).equals(url));
    }

    @Test
    void findUrlByHash_NotFound() {
        String hash = "aK3x5";
        String url = "https://example.com";

        urlMappingRepository.save(new UrlMapping(hash, url));

        assert(databaseLookupService.findUrlByHash("abc1d") == null);
    }

    @Test
    void findUrlByHash_Cacheable() {
        String hash = "aK3x5";
        String oldUrl = "https://example.com";
        String newUrl = "https://sample.com";

        urlMappingRepository.save(new UrlMapping(hash, oldUrl));


        // Create supposed cache
        databaseLookupService.findUrlByHash(hash);

        // Replace UrlMapping with a different one
        urlMappingRepository.deleteById(hash);
        urlMappingRepository.save(new UrlMapping(hash, newUrl));

        // Get result and compare
        String returnedUrl = databaseLookupService.findUrlByHash(hash);
        assert(returnedUrl.equals(oldUrl));
    }

    @Test
    void findHashByUrl_Found() {
        String hash = "aK3x5";
        String url = "https://example.com";

        urlMappingRepository.save(new UrlMapping(hash, url));

        assert(databaseLookupService.findHashByUrl(url).equals(hash));
    }

    @Test
    void findHashByUrl_NotFound() {
        String hash = "aK3x5";
        String url = "https://example.com";

        urlMappingRepository.save(new UrlMapping(hash, url));

        assert(databaseLookupService.findHashByUrl("https://notexample.com") == null);
    }

    @Test
    void findHashByUrl_Cacheable() {
        String oldHash = "aK3x5";
        String newHash = "abc1d";
        String url = "https://example.com";

        urlMappingRepository.save(new UrlMapping(oldHash, url));


        // Create supposed cache
        databaseLookupService.findHashByUrl(url);

        // Replace UrlMapping with a different one
        urlMappingRepository.deleteById(oldHash);
        urlMappingRepository.save(new UrlMapping(newHash, url));

        // Get result and compare
        String returnedHash = databaseLookupService.findHashByUrl(url);
        assert(returnedHash.equals(oldHash));
    }
}
