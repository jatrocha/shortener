package org.cygnus.web.shortener.domain;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public final class Url {

    @NotEmpty(message = "{val.err.original.url.not.empty.or.null}")
    @URL(message = "{val.err.original.url.invalid}")
    private String value;

    public Url() {
    }

    public Url(final String url) {

        this.setValue(url);
    }

    public String getValue() {

        return value;
    }

    public void setValue(final String value) {

        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) {

            return true;
        }

        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        Url url = (Url) o;

        return Objects.equals(value, url.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Url{" + "value='" + value + '\'' + '}';
    }
}
