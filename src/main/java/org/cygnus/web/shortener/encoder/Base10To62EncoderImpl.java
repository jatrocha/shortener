package org.cygnus.web.shortener.encoder;

import org.springframework.stereotype.Service;

@Service
public class Base10To62EncoderImpl implements IEncoder<Long, String> {

    /**
     * Encodes a giver number into a string using Base 62.
     *
     * @param source long number.
     * @return encoded String.
     * @throws IllegalArgumentException when source is null or equals to zero.
     */
    @Override
    public String execute(Long source) {

        if (source == null || source.equals(0L)) {

            throw new IllegalArgumentException("Invalid input: source cannot be null or zero.");
        }

        StringBuilder shortenKey = new StringBuilder();

        while (source > 0) {
            shortenKey.insert(0, POSSIBLE_ALPHABET.charAt((int) (source % BASE)));

            source = source / BASE;
        }

        return shortenKey.toString();
    }
}
