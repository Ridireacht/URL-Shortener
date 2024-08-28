package com.sample.url_shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaveResponseDTO {

    private String shortenedUrl;
    private String qrCodeBase64;
}
