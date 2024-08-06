package com.sample.url_shortener.service.impl;

import com.sample.url_shortener.entity.UrlMapping;
import com.sample.url_shortener.repository.UrlMappingRepository;
import com.sample.url_shortener.service.DatabaseLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseLookupServiceImpl implements DatabaseLookupService {

    private final UrlMappingRepository urlMappingRepository;


    @Override
    @Cacheable("urls")
    public String findUrlByKey(String key) {
        return urlMappingRepository.findById(key)
                .map(UrlMapping::getUrl)
                .orElse(null);
    }

    @Override
    @Cacheable(value = "keys", unless = "#result == null")
    public String findKeyByUrl(String url) {
        return urlMappingRepository.findByUrl(url)
                .map(UrlMapping::getKey)
                .orElse(null);
    }
}
