package ru.innopolis.dao;

import ru.innopolis.model.User;

import java.util.UUID;

public interface UserDAO extends GenericUuidDAO<User, UUID, String> {

    boolean userNameExist(String username);

    boolean emailExist(String email);

    User findUserByLoginOrEmail(String username);

    User findUserByLogin(String name);

}
