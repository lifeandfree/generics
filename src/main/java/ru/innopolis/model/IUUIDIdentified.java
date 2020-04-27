package ru.innopolis.model;

import java.io.Serializable;

public interface IUUIDIdentified<UUID extends Serializable, PK extends Serializable>
        extends Identified<PK> {

    String getUuid();

}
