package org.cygnus.web.shortener.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
//@Table(name = "SHORTENED_URL"
//        , indexes = @Index(name = "index_original_url", columnList = "original_url", unique = true)
//        , uniqueConstraints = @UniqueConstraint(columnNames = "shorten_key", name = "shorten_url_uq_shorten_key"))
public class ShortenedUrl {

    @Column(name = "ORIGINAL_URL")
    private String originalUrl;

    @Id
    @Column(name = "KEY")
    private String key;

    @CreationTimestamp
    @Column(name = "create_time_stamp", updatable = false)
    private LocalDateTime createTimeStamp;

    public ShortenedUrl() {
    }

    public ShortenedUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public ShortenedUrl(String key, String originalUrl) {

        this.originalUrl = originalUrl;

        this.key = key;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String shortenKey) {
        this.key = shortenKey;
    }

    public LocalDateTime getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(LocalDateTime createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }
}
