package org.cygnus.web.shortener.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Base62To10EncoderSpecs {

    @Test
    public void shouldDecodeValidSource() {

        final Long expected = 125L;

        Long actual = new Base62To10EncoderImpl().Execute("cb");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotDecodeWhenSourceIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> new Base62To10EncoderImpl().Execute(null));

        final String expected = "Invalid input: source cannot be null or empty string.";

        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void shouldNotDecodeWhenSourceIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> new Base62To10EncoderImpl().Execute(null));

        final String expected = "Invalid input: source cannot be null or empty string.";

        assertEquals(expected, exception.getMessage());
    }

}
