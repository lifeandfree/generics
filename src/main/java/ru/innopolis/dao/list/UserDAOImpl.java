package ru.innopolis.dao.list;

import org.springframework.stereotype.Component;
import ru.innopolis.dao.UserDAO;
import ru.innopolis.model.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * UserDAOImpl.
 *currentTime
 * @author Ilya_Sukhachev
 */
public class UserDAOImpl extends AbstractUuidDao<User, UUID, String> implements UserDAO {

    public UserDAOImpl() {
        super(User.class, new ArrayList<>());
    }

    @Override
    public boolean userNameExist(String username) {
        throw new UnsupportedOperationException("Not exists operation");
    }

    @Override
    public boolean emailExist(String email) {
        throw new UnsupportedOperationException("Not exists operation");
    }

    @Override
    public User findUserByLoginOrEmail(String username) {
        throw new UnsupportedOperationException("Not exists operation");
    }

    @Override
    public User findUserByLogin(String name) {
        throw new UnsupportedOperationException("Not exists operation");
    }
}
