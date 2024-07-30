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
        String hash = findHashByUrl(url);

        if (hash == null) {
            hash = "someHashHere";
            urlMappingRepository.save(new UrlMapping(hash, url));
        }

        return hash;
    }

    @Override
    public String findUrlByHash(String hash) {
        return urlMappingRepository.findById(hash)
                .map(UrlMapping::getUrl)
                .orElse(null);
    }

    private String findHashByUrl(String url) {
        return urlMappingRepository.findByUrl(url)
                .map(UrlMapping::getHash)
                .orElse(null);
    }
}
