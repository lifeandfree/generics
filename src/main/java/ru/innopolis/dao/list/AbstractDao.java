package ru.innopolis.dao.list;

import ru.innopolis.dao.GenericDAO;
import ru.innopolis.exception.PersistException;
import ru.innopolis.model.CreateAtIdentified;
import ru.innopolis.model.Identified;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public abstract class AbstractDao<T extends Identified<PK>, PK extends Serializable>
        implements GenericDAO<T, PK> {

    private Class<T> clazz;

    protected List<T> elements;

    public AbstractDao(Class<T> elementClass, List<T> list) {
        this.clazz = elementClass;
        this.elements = list;
    }

    @Override
    public T getByPK(PK key) throws PersistException {
        T ob = elements.get((Integer) key);
        return ob;
    }

    @Override
    public Collection<T> getAll() throws PersistException {
        return elements;
    }

    @Override
    public T save(T ob) throws PersistException {
        if (ob instanceof CreateAtIdentified) {
            CreateAtIdentified createAtIdentified = (CreateAtIdentified) ob;
            createAtIdentified.setCreatedAt(LocalDateTime.now());
        }
        elements.add(ob);
        return ob;
    }

    @Override
    public T update(T ob) throws PersistException {
        if (ob instanceof CreateAtIdentified) {
            CreateAtIdentified createAtIdentified = (CreateAtIdentified) ob;
            createAtIdentified.setUpdatedAt(LocalDateTime.now());
        }
        elements.set((Integer) ob.getId(), ob);
        return ob;
    }

    @Override
    public T delete(T ob) throws PersistException {
        elements.remove(ob);
        return ob;
    }

    @Override
    public T deleteByPK(PK key) throws PersistException {
        T ob = getByPK(key);
        elements.remove( (Integer) key);
        return ob;
    }

    @Override
    public Collection<T> addAll(Collection<T> obs) throws PersistException {
        elements.addAll(obs);
        return obs;
    }

}