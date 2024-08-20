package com.sample.url_shortener.service;

import com.sample.url_shortener.entity.UrlMapping;
import com.sample.url_shortener.repository.UrlMappingRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class DatabaseLookupServiceTests {

    @Autowired
    private DatabaseLookupService databaseLookupService;
    @Autowired
    private UrlMappingRepository urlMappingRepository;


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
}
