package org.cygnus.web.shortener.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetry")
public class Telemetry {

    @Id
    @Column(name = "key")
    private String key;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @Column(name = "lastAccess")
    private LocalDateTime lastAccess;

    @Column(name = "hitCount")
    private int hitCount;

    public Telemetry() {
    }

    public Telemetry(String key) {
        this.key = key;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public LocalDateTime getLastAccess() {
        return this.lastAccess;
    }

    public void setLastAccess(LocalDateTime dateTime) {
        this.lastAccess = dateTime;
    }

    public int getHitCount() {
        return this.hitCount;
    }

    public void addHitCount() {
        this.hitCount = ++this.hitCount;
    }
}
