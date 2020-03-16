package org.cygnus.web.shortener.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @CreationTimestamp
    @Column(name = "create_time_stamp", updatable = false)
    private LocalDateTime createTimeStamp;

    public ShortenedUrl() {
    }

    public ShortenedUrl(final String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public ShortenedUrl(final String key, final String originalUrl) {

        this.originalUrl = originalUrl;

        this.key = key;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(final String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String shortenKey) {
        this.key = shortenKey;
    }

    public LocalDateTime getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(final LocalDateTime createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }
}
