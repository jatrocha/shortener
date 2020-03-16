package org.cygnus.web.shortener.controllers;

import org.cygnus.web.shortener.entities.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/telemetry")
public final class TelemetryController {

    private final JpaRepository<Telemetry, String> telemetryRepository;

    public TelemetryController(JpaRepository<Telemetry, String> repository) {

        this.telemetryRepository = repository;
    }

    @GetMapping("")
    public ResponseEntity<List<Telemetry>> getAll() {

        Optional<List<Telemetry>> telemetries = Optional.of(telemetryRepository.findAll());

        return ResponseEntity.of(telemetries);
    }

    @GetMapping("/{key}")
    public ResponseEntity<List<Telemetry>> getByKey(final @PathVariable("key") String key) {

        Optional<List<Telemetry>> telemetries =
                Optional.of(telemetryRepository.findAllById(Stream.of(key).collect(Collectors.toList())));

        return ResponseEntity.of(telemetries);
    }

}
