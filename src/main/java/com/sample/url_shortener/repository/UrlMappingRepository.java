package com.sample.url_shortener.repository;

import com.sample.url_shortener.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, String> {

    Optional<UrlMapping> findByUrl(String url);
}
