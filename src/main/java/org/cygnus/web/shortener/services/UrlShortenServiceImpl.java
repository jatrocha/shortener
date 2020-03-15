package org.cygnus.web.shortener.services;

import org.apache.commons.lang3.StringUtils;
import org.cygnus.web.shortener.converter.IEncoder;
import org.cygnus.web.shortener.domain.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UrlShortenServiceImpl implements IUrlShortenService {

    @Value("${base.url}")
    private String BASE_URL;

    @Value("${shorten.url.prefix}")
    private String SHORTEN_URL_PREFIX;

    @Autowired
    private IEncoder<Long, String> encoder;

    public Url Execute(final Url source) {

        String key = generateKey(source.getValue());

        return new Url(generateKey(source.getValue()));
    }

    protected final String generateKey(final String source) {
        if (source == null || StringUtils.EMPTY.equals(source)) {

            throw new IllegalArgumentException("Invalid input: source cannot be null or empty.");
        }

        return encoder.Execute(new Long(source.hashCode()));
    }
}
