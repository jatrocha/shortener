package org.cygnus.web.shortener.domain;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Url {

    @NotEmpty(message = "{val.err.original.url.not.empty.or.null}")
    @URL(message = "{val.err.original.url.invalid}")
    private String value;

    public Url() {
    }

    public Url(final String value) {

        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Url url = (Url) o;

        return Objects.equals(value, url.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
