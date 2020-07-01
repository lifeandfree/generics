package ru.innopolis.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.innopolis.dao.UserDAO;
import ru.innopolis.dao.UserInfoDAO;
import ru.innopolis.model.User;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * UserServiceImpl.
 *
 * @author lifeandfree
 */
@Service
@Scope("prototype")
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    private UserDAO userDAO;
    private UserInfoDAO userInfoDAO;
    private LocalDateTime currentTime;

    public UserServiceImpl(@Qualifier("getUserDAO") UserDAO userDAO, UserInfoDAO userInfoDAO) {
        this.userDAO = userDAO;
        this.userInfoDAO = userInfoDAO;
    }
//
//    @Autowired
//    public void setUserDAO(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }

    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct");
    }

    @Override
    public void addUser(User user) {
        log.info("now: "+ LocalDateTime.now() + " currentTime: " + currentTime);
        user.setCreatedAt(currentTime);
        userInfoDAO.save(user.getUserInfo());
        userDAO.save(user);
    }

    @Override
    public void setTime(LocalDateTime now) {
        currentTime = now;
    }
}
