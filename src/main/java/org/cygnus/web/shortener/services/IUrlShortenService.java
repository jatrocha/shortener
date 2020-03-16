package org.cygnus.web.shortener.services;

import org.cygnus.web.shortener.domain.Url;

public interface IUrlShortenService {

    Url execute(Url source);
}
