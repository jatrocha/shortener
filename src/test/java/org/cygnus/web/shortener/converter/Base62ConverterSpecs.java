package org.cygnus.web.shortener.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Base62ConverterSpecs {

    @Test
    public void shouldEncodeWhenSourceNotZero()
    {
        final String expected = "cb";

        String actual = new Base62EncoderImpl().Execute(125L);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotEncodeWhenSourceEqualsZero()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Base62EncoderImpl().Execute(null));

        final String expected = "Invalid input: source cannot be null or zero.";

        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void shouldNotEncodeWhenSourceIsNull()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Base62EncoderImpl().Execute(null));

        final String expected = "Invalid input: source cannot be null or zero.";

        assertEquals(expected, exception.getMessage());
    }

}
