package org.cygnus.web.shortener.services;

import org.cygnus.web.shortener.domain.Url;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UrlShortenServiceTests {

    @Autowired
    private IUrlShortenService service;

    @Value("${base.url}")
    private String BASE_URL;

    @Value("${shorten.url.prefix}")
    private String SHORTEN_URL_PREFIX;

    @Test
    public void shouldShortenUrl() {
        final String url =
                "https://cygnus-x1.visualstudio.com/Neueda/_sprints/taskboard/Neueda%20Team/Neueda/Iteration%201" +
                        "?workitem=76";

        Url input = new Url(url);

        String baseUrl = BASE_URL + SHORTEN_URL_PREFIX + "b9eNGS";

        final Url expected = new Url(baseUrl);

        Url actual = service.Execute(input);

        assertEquals(expected, actual);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotGenerateKeyWhenUrlIsInvalidNull() {

        new UrlShortenServiceImpl().generateKey(null);
    }

}
