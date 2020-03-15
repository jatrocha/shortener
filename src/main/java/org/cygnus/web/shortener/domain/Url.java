package org.cygnus.web.shortener.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Url {

    @Id
    @GeneratedValue
    private Integer id;

}
