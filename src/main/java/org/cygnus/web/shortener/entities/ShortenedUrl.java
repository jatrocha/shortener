package org.cygnus.web.shortener.entities;

import javax.persistence.*;

@Entity
@Table(name = "shortened_url",
        indexes = @Index(name = "index_original_url", columnList = "original_url", unique = true),
        uniqueConstraints = @UniqueConstraint(columnNames = "KEY", name = "shorten_url_uq_shorten_key"))
public final class ShortenedUrl {

    @Column(name = "original_url")
    private String originalUrl;

    @Id
    @Column(name = "key")
    private String key;

    public ShortenedUrl() {
    }

    public ShortenedUrl(final String key, final String originalUrl) {

        this.originalUrl = originalUrl;

        this.key = key;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

}
