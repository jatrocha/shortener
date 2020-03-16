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

    @Value("${base.url}")
    private String baseUrl;

    private final IEncoder<Long, String> encoder;

    private final JpaRepository<ShortenedUrl, String> repository;

    public UrlShortenServiceImpl(final IEncoder<Long, String> encoder,
                                 final JpaRepository<ShortenedUrl, String> repository) {

        this.encoder = encoder;

        this.repository = repository;
    }

    public Url execute(final Url source) {

        String key = generateKey(source.getValue());

        Url result = new Url(baseUrl + key);

        if (!repository.findById(key).isPresent()) {

            // register hits for equal keys

            repository.save(new ShortenedUrl(key, source.getValue()));
        } else {
            
            // register hits for new keys.
        }

        return result;
    }

    protected final String generateKey(final String source) {

        return encoder.execute((long) source.hashCode());
    }
}
