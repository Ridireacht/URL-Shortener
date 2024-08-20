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
    void findUrlByKey_Found() {

    }

    @Test
    void findUrlByKey_NotFound() {

    }

    @Test
    void findUrlByKey_Caching() {

    }

    @Test
    void findKeyByUrl_Found() {

    }

    @Test
    void findKeyByUrl_NotFound() {

    }

    @Test
    void findKeyByUrl_Caching() {

    }
}
