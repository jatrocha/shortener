package org.cygnus.web.shortener.repository;

import org.cygnus.web.shortener.entities.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, String> {
}
