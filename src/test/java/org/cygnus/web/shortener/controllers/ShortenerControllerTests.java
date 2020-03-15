package org.cygnus.web.shortener.controllers;

import io.restassured.http.ContentType;
import org.cygnus.web.shortener.domain.Url;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ShortenerControllerTests {

    @Value("${base.url}")
    private String BASE_URL;

    @Value("${shorten.url.prefix}")
    private String SHORTEN_URL_PREFIX;

    @Test
    public void should() {
        final String url =
                "https://cygnus-x1.visualstudio.com/Neueda/_sprints/taskboard/Neueda%20Team/Neueda/Iteration%201" +
                        "?workitem=76";

        Url input = new Url(url);

        String expected = BASE_URL + SHORTEN_URL_PREFIX + "b9eNGS";

        given()
                .contentType("application/json")
                .body(input)
                .when()
                .post("/api/v1/url/shorten")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("value", equalTo(expected));
    }

}
