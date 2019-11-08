package ru.innopolis.dao.list;

import ru.innopolis.dao.UserInfoDAO;
import ru.innopolis.model.User;
import ru.innopolis.model.UserInfo;

import java.util.ArrayList;
import java.util.UUID;

/**
 * UserInfoDAOImpl.
 *
 * @author Ilya_Sukhachev
 */
public class UserInfoDAOImpl extends AbstractDao<UserInfo, User> implements UserInfoDAO {

    public UserInfoDAOImpl(Class<? extends UserInfo> elementClass) {
        super((Class<UserInfo>) elementClass, new ArrayList<>());
    }
}
