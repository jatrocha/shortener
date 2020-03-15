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
public class ShortenerController {

    private final IUrlShortenService service;

    public ShortenerController(IUrlShortenService service) {

        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@Validated @RequestBody Url url) {

        Url result = service.Execute(url);

        return ResponseEntity.of(Optional.of(result));
    }
}
