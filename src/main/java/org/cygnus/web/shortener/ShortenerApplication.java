package org.cygnus.web.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShortenerApplication {

    protected ShortenerApplication() {
    }

    public static void main(final String[] args) {

        SpringApplication.run(ShortenerApplication.class, args);
    }

}
