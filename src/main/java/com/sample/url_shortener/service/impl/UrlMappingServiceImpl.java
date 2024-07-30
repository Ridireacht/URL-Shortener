package com.sample.url_shortener.service.impl;

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
        return null;
    }

    @Override
    public String findUrlByHash(String hash) {
        return null;
    }
}
