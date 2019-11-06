package ru.innopolis.dao.map;

import ru.innopolis.dao.GenericUuidDAO;
import ru.innopolis.exception.PersistException;
import ru.innopolis.model.IUUIDIdentified;
import ru.innopolis.model.User;

import java.io.Serializable;
import java.util.Map;

/**
 * AbstractUuidDao.
 *
 * @author Ilya_Sukhachev
 */
public class AbstractUuidDao<E extends IUUIDIdentified<UUID, PK>, PK extends Serializable,
        UUID extends Serializable>
        extends AbstractDao<E, PK> implements GenericUuidDAO<E, PK, UUID> {

    public AbstractUuidDao(Class<E> elementClass, Map<PK, E> map) {
        super(elementClass, map);
    }

    @Override
    public E getByUUID(UUID key) throws PersistException {
        for (E el:elements.values()) {
            if (el.getUuid().equals(key)) {
                return el;
            }
        }
        return null;
    }
}
