package org.cygnus.web.shortener.services;

import org.cygnus.web.shortener.converter.Base10To62EncoderImpl;
import org.cygnus.web.shortener.domain.Url;
import org.cygnus.web.shortener.entities.ShortenedUrl;
import org.cygnus.web.shortener.repository.ShortenedUrlRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UrlShortenServiceTests {

    @Autowired
    private IUrlShortenService service;

    @Value("${base.url}")
    private String BASE_URL;

    @Test
    public void shouldShortenANewUrl() {
        final String url =
                "https://cygnus-x1.visualstudio.com/Neueda/_sprints/taskboard/Neueda%20Team/Neueda/Iteration%201" +
                        "?workitem=76";

        Url input = new Url(url);

        String baseUrl = BASE_URL + "b9eNGS";

        final Url expected = new Url(baseUrl);

        Url actual = service.execute(input);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAlreadyShortenedUrl() {
        final String url =
                "https://cygnus-x1.visualstudio.com/Neueda/_sprints/taskboard/Neueda%20Team/Neueda/Iteration%201" +
                        "?workitem=76";

        Mockery context = new Mockery();

        final ShortenedUrlRepository mock = context.mock(ShortenedUrlRepository.class);

        context.checking(new Expectations() {{
            oneOf(mock).findById("b9eNGS");
            will(returnValue(Optional.of(new ShortenedUrl("b9eNGS", url))));
        }});

        Url input = new Url(url);

        new UrlShortenServiceImpl(new Base10To62EncoderImpl(), mock).execute(input);

        context.assertIsSatisfied();
    }

}
