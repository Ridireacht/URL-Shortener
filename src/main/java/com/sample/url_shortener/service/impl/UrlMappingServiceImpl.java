package com.sample.url_shortener.service.impl;

import com.sample.url_shortener.entity.UrlMapping;
import com.sample.url_shortener.repository.UrlMappingRepository;
import com.sample.url_shortener.service.UrlMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlMappingServiceImpl implements UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;


    @Override
    public String saveUrl(String url) {
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(url);

        String hash = "";
        urlMapping.setHash(hash);

        urlMappingRepository.save(urlMapping);

        return hash;
    }

    @Override
    public String findUrlByHash(String hash) {
        var dbRecord = urlMappingRepository.findById(hash);
        return dbRecord.map(UrlMapping::getOriginalUrl).orElse(null);
    }
}
