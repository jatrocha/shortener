package org.cygnus.web.shortener.converter;

public interface IEncoder<S, T> {

    T Execute(S source);
}
