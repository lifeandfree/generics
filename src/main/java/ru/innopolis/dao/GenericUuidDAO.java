package ru.innopolis.dao;

import ru.innopolis.exception.PersistException;
import ru.innopolis.model.IUUIDIdentified;

import java.io.Serializable;

/**
 * интерфейс управления персистентным состоянием объектов содержащих uuid
 *
 * @param <E>
 *         тип объекта персистенции
 * @param <UUID>
 *         тип uuid ключа
 * @param <PK>
 *         тип ключа
 */
public interface GenericUuidDAO<E extends IUUIDIdentified<UUID, PK>, PK extends Serializable,
        UUID extends Serializable> extends GenericDAO<E, PK> {

    /**
     * Возвращает объект соответствующий записи с uuid ключом key или null
     */
    public E getByUUID(UUID key) throws PersistException;

}
