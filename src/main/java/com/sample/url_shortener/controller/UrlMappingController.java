package com.sample.url_shortener.controller;

import com.sample.url_shortener.dto.SaveRequestDTO;
import com.sample.url_shortener.dto.SaveResponseDTO;
import com.sample.url_shortener.service.UrlMappingService;
import com.sample.url_shortener.util.QRCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
public class UrlMappingController {

    @Value("${APP_DOMAIN}")
    private String appDomain;

    private final UrlMappingService urlMappingService;


    @PostMapping("/")
    public ResponseEntity<Object> saveUrl(@RequestBody SaveRequestDTO saveRequestDTO) {
        if (!isURL(saveRequestDTO.getUrl())) {
            return ResponseEntity.badRequest().body("Invalid URL format");
        }

        // Remove all unnecessary '/' at the end
        String url = saveRequestDTO.getUrl().replaceAll("/+$", "");

        if (url.length() > 1000) {
            return ResponseEntity.badRequest().body("URL too long (1000 characters at max)");
        }

        String shortenedId = urlMappingService.saveUrl(url);
        String shortenedUrl = appDomain + "/r/" + shortenedId;

        String qrCodeBase64 = QRCodeGenerator.generateQrCode(shortenedUrl);

        SaveResponseDTO response = new SaveResponseDTO(shortenedUrl, qrCodeBase64);
        return ResponseEntity.ok().body(response);
    }

    
    private static boolean isURL(String input) {
        String URL_REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";

        Pattern pattern = Pattern.compile(URL_REGEX);

        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
