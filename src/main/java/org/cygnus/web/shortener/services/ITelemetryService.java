package org.cygnus.web.shortener.services;

public interface ITelemetryService {

    void registerNew(String input);

    void registerHit(String input);

}
