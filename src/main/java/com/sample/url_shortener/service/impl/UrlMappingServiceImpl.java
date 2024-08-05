package com.sample.url_shortener.service.impl;

import com.sample.url_shortener.entity.UrlMapping;
import com.sample.url_shortener.repository.UrlMappingRepository;
import com.sample.url_shortener.service.DatabaseLookupService;
import com.sample.url_shortener.service.UrlMappingService;
import com.sample.url_shortener.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlMappingServiceImpl implements UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;
    private final DatabaseLookupService databaseLookupService;


    @Override
    public String saveUrl(String url) {
        String hash = databaseLookupService.findHashByUrl(url);

        if (hash == null) {
            hash = HashUtil.generateHashForUrl(url);
            urlMappingRepository.save(new UrlMapping(hash, url));
        }

        return hash;
    }
}
