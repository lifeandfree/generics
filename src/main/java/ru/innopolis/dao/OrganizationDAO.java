package ru.innopolis.dao;

import ru.innopolis.model.Identified;
import ru.innopolis.model.Organization;
import ru.innopolis.model.User;

import java.io.Serializable;

public interface OrganizationDAO<T extends Identified<PK>, PK extends Serializable>
        extends GenericDAO<T, PK> {

    Organization getByName(String organizationName);

    Organization getByUser(User user);
}
