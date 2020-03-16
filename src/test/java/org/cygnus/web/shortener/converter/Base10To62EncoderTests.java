package org.cygnus.web.shortener.converter;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Base10To62EncoderTests {

    @Test
    public void shouldEncodeWhenSourceNotZero() {
        final String expected = "cb";

        String actual = new Base10To62EncoderImpl().execute(125L);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotEncodeWhenSourceEqualsZero() {

        new Base10To62EncoderImpl().execute(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotEncodeWhenSourceIsNull() {

        new Base10To62EncoderImpl().execute(null);
    }

}
