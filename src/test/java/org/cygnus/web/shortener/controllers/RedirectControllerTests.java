package org.cygnus.web.shortener.controllers;

import org.cygnus.web.shortener.domain.Url;
import org.cygnus.web.shortener.entities.ShortenedUrl;
import org.cygnus.web.shortener.services.IUrlShortenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RedirectControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IUrlShortenService service;

    @Autowired
    private RedirectController controller;

    @Value("${base.url}")
    private String BASE_URL;

    @Autowired
    private JpaRepository<ShortenedUrl, String> repository;

    @Test
    public void shouldRedirectToOriginalUrl() {
        final String expected = "https://www.microsoft.com/";

        Url shortened = service.execute(new Url(expected));

        String key = shortened.getValue().split("/")[3];

        assertThat(controller.redirectToOriginalUrl(key).getViewName()
                , equalTo("redirect:" + expected));

    }

    @Test
    public void shouldNotRedirectWhenShortenUrlNotFound() {
        assertThat(restTemplate.getForObject(BASE_URL + "xpto",
                String.class), containsString("<h1>Url not found</h1>"));
    }

}
