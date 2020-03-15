package org.cygnus.web.shortener.converter;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Base62To10EncoderTests {

    @Test
    public void shouldDecodeValidSource() {

        final Long expected = 125L;

        Long actual = new Base62To10EncoderImpl().Execute("cb");

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDecodeWhenSourceIsEmpty() {

        new Base62To10EncoderImpl().Execute(StringUtils.EMPTY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDecodeWhenSourceIsNull() {

        new Base62To10EncoderImpl().Execute(null);
    }

}
