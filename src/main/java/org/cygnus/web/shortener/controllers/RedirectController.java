package org.cygnus.web.shortener.controllers;

import org.cygnus.web.shortener.entities.ShortenedUrl;
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

    public RedirectController(final JpaRepository<ShortenedUrl, String> repository) {

        this.shortenedUrlRepository = repository;
    }

    @GetMapping("/{key}")
    public ModelAndView redirectToOriginalUrl(final @PathVariable("key") String url) {

        Optional<ShortenedUrl> result = shortenedUrlRepository.findById(url);

        return result
                .map(shortenedUrl -> new ModelAndView("redirect:" + shortenedUrl.getOriginalUrl()))
                .orElseGet(() -> new ModelAndView(("url_not_found")));
    }
}
