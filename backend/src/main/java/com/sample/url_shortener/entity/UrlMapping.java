package com.sample.url_shortener.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="url_mappings")
@NoArgsConstructor
@AllArgsConstructor
public class UrlMapping {

    @Id
    @Column(unique = true, nullable = false)
    private String hash;

    @Column(unique = true, nullable = false)
    private String url;
}
