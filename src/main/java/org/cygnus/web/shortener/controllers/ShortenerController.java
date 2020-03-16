package org.cygnus.web.shortener.controllers;

import org.cygnus.web.shortener.domain.Url;
import org.cygnus.web.shortener.services.IUrlShortenService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public final class ShortenerController {

    private final IUrlShortenService shortenService;

    public ShortenerController(final IUrlShortenService service) {

        this.shortenService = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(final @Validated @RequestBody Url url) {

        Url result = shortenService.execute(url);

        return ResponseEntity.of(Optional.of(result));
    }
}
