package org.cygnus.web.shortener.encoder;

public interface IEncoder<S, T> {

    String POSSIBLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    long BASE = POSSIBLE_ALPHABET.length();

    T execute(S source);
}
