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
    public String findUrlByHash(String hash) {
        return urlMappingRepository.findById(hash)
                .map(UrlMapping::getUrl)
                .orElse(null);
    }

    @Override
    @Cacheable(value = "hashes", unless = "#result == null")
    public String findHashByUrl(String url) {
        return urlMappingRepository.findByUrl(url)
                .map(UrlMapping::getHash)
                .orElse(null);
    }
}
