package org.cygnus.web.shortener.services;

import org.cygnus.web.shortener.converter.IEncoder;
import org.cygnus.web.shortener.domain.Url;
import org.cygnus.web.shortener.entities.ShortenedUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UrlShortenServiceImpl implements IUrlShortenService {

    @Value("${base.url}")
    private String BASE_URL;

    @Value("${shorten.url.prefix}")
    private String SHORTEN_URL_PREFIX;

    private final IEncoder<Long, String> encoder;

    private final JpaRepository<ShortenedUrl, String> repository;

    public UrlShortenServiceImpl(IEncoder<Long, String> encoder, JpaRepository<ShortenedUrl, String> repository) {

        this.encoder = encoder;

        this.repository = repository;
    }

    public Url Execute(final Url source) {

        String key = generateKey(source.getValue());

        if (!repository.findById(key).isPresent()) {

            repository.save(new ShortenedUrl(key, source.getValue()));
        }

        return new Url(BASE_URL + SHORTEN_URL_PREFIX + key);
    }

    protected final String generateKey(final String source) {

        return encoder.Execute((long) source.hashCode());
    }
}
