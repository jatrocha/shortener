package org.cygnus.web.shortener.controllers;

import org.cygnus.web.shortener.entities.ShortenedUrl;
import org.cygnus.web.shortener.services.ITelemetryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping()
public final class RedirectController {

    private final JpaRepository<ShortenedUrl, String> shortenedUrlRepository;

    private ITelemetryService telemetryService;

    public RedirectController(final JpaRepository<ShortenedUrl, String> repository,
                              final ITelemetryService service) {

        this.shortenedUrlRepository = repository;

        this.telemetryService = service;
    }

    @GetMapping("/{key}")
    public ModelAndView redirectToOriginalUrl(final @PathVariable("key") String url) {

        Optional<ShortenedUrl> result = shortenedUrlRepository.findById(url);

        if (result.isPresent()) {
            telemetryService.registerHit(result.get().getKey());

            return new ModelAndView("redirect:" + result.get().getOriginalUrl());
        }

        return new ModelAndView("url_not_found");
    }
}
