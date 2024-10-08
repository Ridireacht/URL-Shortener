package com.sample.url_shortener.controller;

import com.sample.url_shortener.dto.SaveRequestDTO;
import com.sample.url_shortener.dto.SaveResponseDTO;
import com.sample.url_shortener.service.UrlMappingService;
import com.sample.url_shortener.util.QRCodeGenerator;
import com.sample.url_shortener.util.UrlUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlMappingController {

    @Value("${APP_DOMAIN}")
    private String appDomain;

    private final UrlMappingService urlMappingService;


    @PostMapping("/api/v1/urls")
    public ResponseEntity<Object> saveUrl(@RequestBody SaveRequestDTO saveRequestDTO) {
        if (!UrlUtil.isURL(saveRequestDTO.getUrl())) {
            return ResponseEntity.badRequest().body("Invalid URL format");
        }

        // Remove all unnecessary '/' at the end
        String url = saveRequestDTO.getUrl().replaceAll("/+$", "");

        if (url.length() > 1000) {
            return ResponseEntity.badRequest().body("URL too long (1000 characters at max)");
        }

        if (!UrlUtil.doesDomainExist(url)) {
            return ResponseEntity.badRequest().body("URL's domain doesn't exist");
        }


        String shortenedId = urlMappingService.saveUrl(url);
        String shortenedUrl = appDomain + "/r/" + shortenedId;

        String qrCodeBase64 = QRCodeGenerator.generate(shortenedUrl);

        SaveResponseDTO response = new SaveResponseDTO(shortenedUrl, qrCodeBase64);
        return ResponseEntity.ok().body(response);
    }
}
