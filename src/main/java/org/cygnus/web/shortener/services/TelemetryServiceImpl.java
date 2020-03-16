package org.cygnus.web.shortener.services;

import org.cygnus.web.shortener.entities.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Transactional
@Service
public class TelemetryServiceImpl implements ITelemetryService {

    private final JpaRepository<Telemetry, String> telemetryRepository;

    public TelemetryServiceImpl(JpaRepository<Telemetry, String> repository) {

        this.telemetryRepository = repository;
    }

    @Override
    public void registerNew(String key) {

        Telemetry entity = new Telemetry(key);

        telemetryRepository.save(entity);
    }

    @Override
    public void registerHit(String key) {

        Telemetry entity = telemetryRepository.findById(key).get();

        entity.setLastAccess(LocalDateTime.now());

        entity.addHitCount();

        telemetryRepository.save(entity);
    }

}
