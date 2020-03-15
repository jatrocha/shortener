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
public class RedirectController {

    private final JpaRepository<ShortenedUrl, String> repository;

    public RedirectController(JpaRepository<ShortenedUrl, String> repository) {

        this.repository = repository;
    }

    @GetMapping("/{shortenStr}")
    public ModelAndView redirectToOriginalUrl(@PathVariable("shortenStr") String url) {

        Optional<ShortenedUrl> result = repository.findById(url);

        return result
                .map(shortenedUrl -> new ModelAndView("redirect:" + shortenedUrl.getOriginalUrl()))
                .orElseGet(() -> new ModelAndView(("url_not_found")));
    }
}
