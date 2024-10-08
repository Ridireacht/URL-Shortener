package com.sample.url_shortener.service.impl;

import com.sample.url_shortener.entity.UrlMapping;
import com.sample.url_shortener.repository.UrlMappingRepository;
import com.sample.url_shortener.service.DatabaseLookupService;
import com.sample.url_shortener.service.UrlMappingService;
import com.sample.url_shortener.util.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlMappingServiceImpl implements UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;
    private final DatabaseLookupService databaseLookupService;


    @Override
    public String processUrl(String url) {
        log.info("Started processing the following URL: {}", url);
        String hash = databaseLookupService.findHashByUrl(url);

        if (hash == null) {
            do {
                hash = RandomStringGenerator.generateRandomString(6);
            } while (urlMappingRepository.existsById(hash));

            try {
                urlMappingRepository.save(new UrlMapping(hash, url));
            } catch (DataIntegrityViolationException e) {
                log.info("Hash collision occurred for URL \"{}\", retrying...", url);
                return processUrl(url);
            }
        }

        log.info("URL successfully shortened: {} -> {}", url, hash);
        return hash;
    }
}
