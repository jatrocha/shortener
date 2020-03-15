package org.cygnus.web.shortener.repository;

import org.cygnus.web.shortener.entities.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, String> {
}
