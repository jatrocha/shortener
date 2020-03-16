package org.cygnus.web.shortener.services;

import org.cygnus.web.shortener.domain.Url;
import org.cygnus.web.shortener.encoder.IEncoder;
import org.cygnus.web.shortener.entities.ShortenedUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UrlShortenServiceImpl implements IUrlShortenService {

    private final IEncoder<Long, String> encoder;

    private final JpaRepository<ShortenedUrl, String> repository;

    private final ITelemetryService telemetryService;

    @Value("${base.url}")
    private String baseUrl;

    public UrlShortenServiceImpl(final IEncoder<Long, String> encoder,
                                 final JpaRepository<ShortenedUrl, String> repository,
                                 final ITelemetryService service) {

        this.encoder = encoder;

        this.repository = repository;

        this.telemetryService = service;
    }

    public Url execute(final Url source) {

        String key = generateKey(source.getValue());

        if (!repository.findById(key).isPresent()) {

            telemetryService.registerNew(key);

            repository.save(new ShortenedUrl(key, source.getValue()));
        }

        return new Url(baseUrl + key);
    }

    protected final String generateKey(final String source) {

        return encoder.execute((long) source.hashCode());
    }
}
