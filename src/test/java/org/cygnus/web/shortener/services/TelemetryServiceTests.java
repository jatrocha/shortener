package org.cygnus.web.shortener.services;

import org.cygnus.web.shortener.entities.Telemetry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TelemetryServiceTests {

    @Autowired
    private ITelemetryService service;

    @Autowired
    private JpaRepository<Telemetry, String> repository;

    @Test
    public void shouldRegisterNewUrl() {

        final String input = "b9eNGS";

        service.registerNew(input);

        Optional<Telemetry> entity = repository.findById(input);

        assertTrue(entity.isPresent());

        assertNotNull(entity.get().getCreated());

        assertNull(entity.get().getLastAccess());
    }

    @Test
    public void shouldRegisterFollowingHits() {

        final String input = "b9eNGS";

        service.registerNew(input);

        service.registerHit(input);

        Optional<Telemetry> entity = repository.findById(input);

        assertTrue(entity.isPresent());

        assertNotNull(entity.get().getCreated());

        assertNotNull(entity.get().getLastAccess());
    }

    @Test
    public void shouldRegisterHits() {

        final int expected = 1;

        final String input = "b9eNGS";

        service.registerNew(input);

        service.registerHit(input);

        Optional<Telemetry> entity = repository.findById(input);

        assertTrue(entity.isPresent());

        assertNotNull(entity.get().getCreated());

        assertNotNull(entity.get().getLastAccess());

        assertEquals(expected, entity.get().getHitCount());
    }

}
