package ru.innopolis.dao.list;

import ru.innopolis.dao.GenericUuidDAO;
import ru.innopolis.exception.PersistException;
import ru.innopolis.model.IUUIDIdentified;

import java.io.Serializable;
import java.util.List;

/**
 * AbstractUuidDao.
 *
 * @author Ilya_Sukhachev
 */
public class AbstractUuidDao<E extends IUUIDIdentified<UUID, PK>, PK extends Serializable,
        UUID extends Serializable>
        extends AbstractDao<E, PK> implements GenericUuidDAO<E, PK, UUID> {

    public AbstractUuidDao(Class<E> elementClass, List<E> list) {
        super(elementClass, list);
    }


    @Override
    public E getByUUID(UUID key) throws PersistException {
        for (E el:elements) {
            if (el.getUuid().equals(key)) {
                return el;
            }
        }
        return null;
    }
}
