package ru.innopolis.service.provider;

import ru.innopolis.exception.PersistException;
import ru.innopolis.model.Identified;

import javax.persistence.Table;

abstract class SqlProviderSupport {

    public String getTableName(Class<? extends Identified> entityClass) {
        Table table = entityClass.getAnnotation(Table.class);
        if (table != null) {
            return table.name();
        } else {
            throw new PersistException("undefined @Table, need name(@Table(name)) ");
        }
    }

}