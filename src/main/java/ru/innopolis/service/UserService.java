package ru.innopolis.service;

import ru.innopolis.model.User;

import java.time.LocalDateTime;

/**
 * UserService.
 *
 * @author lifeandfree
 */
public interface UserService {
    void addUser(User user);

    void setTime(LocalDateTime now);
}
