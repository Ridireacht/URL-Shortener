package com.sample.url_shortener.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="url_mappings")
public class UrlMapping {

    @Id
    @Column(name = "hash", unique = true, nullable = false)
    private String hash;

    @Column(name = "url", unique = true, nullable = false)
    private String url;

    public UrlMapping() {}

    public UrlMapping(String hash, String url) {
        this.hash = hash;
        this.url = url;
    }
}
