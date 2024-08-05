package com.sample.url_shortener.service.impl;

import com.sample.url_shortener.entity.UrlMapping;
import com.sample.url_shortener.repository.UrlMappingRepository;
import com.sample.url_shortener.service.DatabaseLookupService;
import com.sample.url_shortener.service.UrlMappingService;
import com.sample.url_shortener.util.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlMappingServiceImpl implements UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;
    private final DatabaseLookupService databaseLookupService;


    @Override
    public String saveUrl(String url) {
        String key = databaseLookupService.findKeyByUrl(url);

        if (key == null) {
            key = RandomStringGenerator.generateRandomString(6);
            urlMappingRepository.save(new UrlMapping(key, url));
        }

        return key;
    }
}
