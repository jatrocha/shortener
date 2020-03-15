package org.cygnus.web.shortener.converter;

import org.apache.commons.lang3.StringUtils;

public class Base62To10EncoderImpl implements IEncoder<String, Long> {

    /**
     * Decodes a given string back to a number.
     *
     * @param source string encoded using base 62.
     * @return decoded Number.
     * @throws IllegalArgumentException when source is null or empty.
     */
    @Override
    public Long Execute(final String source) {

        if (source == null || StringUtils.EMPTY.equals(source)) {

            throw new IllegalArgumentException("Invalid input: source cannot be null or empty string.");
        }

        long num = 0;

        for (int i = 0; i < source.length(); i++) {

            num = num * BASE + POSSIBLE_ALPHABET.indexOf(source.charAt(i));
        }

        return num;
    }
}
