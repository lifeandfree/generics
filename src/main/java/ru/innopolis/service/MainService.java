package ru.innopolis.service;

import ru.innopolis.dao.OrganizationDAO;
import ru.innopolis.dao.UserDAO;
import ru.innopolis.dao.UserInfoDAO;
import ru.innopolis.dao.map.OrganizationDAOImpl;
import ru.innopolis.dao.map.UserDAOImpl;
import ru.innopolis.dao.map.UserInfoDAOImpl;
import ru.innopolis.model.ExtraUserInfo;
import ru.innopolis.model.Organization;
import ru.innopolis.model.User;
import ru.innopolis.model.UserInfo;
import ru.innopolis.model.enums.OrganizationStatus;
import ru.innopolis.model.enums.Role;
import ru.innopolis.model.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

/**
 * MainService.
 *
 * @author Ilya_Sukhachev
 */
public class MainService {

    private static UserDAO userDAO = new UserDAOImpl();
    private static OrganizationDAO organizationDAO = new OrganizationDAOImpl();
    private static UserInfoDAO userInfoDAO = new UserInfoDAOImpl(ExtraUserInfo.class);

    public static void main(String[] args) {

        User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(),"Admin1", "test@test.ru");

        UserInfo userInfo = new UserInfo();
        userInfo.setId(user);
        userInfo.setFirstName("name");
        userInfo.setSecondName("secondname");
        userInfo.setLastName("lastname");
        userInfoDAO.save(userInfo);
        System.out.println(userInfoDAO.getByPK(user));

        ExtraUserInfo extraUserInfo = new ExtraUserInfo();
        extraUserInfo.setAddress("Address2");
        extraUserInfo.setId(user);
        extraUserInfo.setFirstName("name2");
        extraUserInfo.setSecondName("secondname2");
        extraUserInfo.setLastName("lastname2");
        userInfoDAO.save(extraUserInfo);

        System.out.println(userInfoDAO.getByPK(user)); //todo получим другой элемент
        User user2 = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(),"Admin1", "test@test.ru");
        user2.setPassword("password");
        user2.setRole(Role.ROLE_ADMIN);
        user2.setUserStatus(UserStatus.STATUS_ACTIVED);
        user2.setUserInfo(userInfo);
        userDAO.save(user2);

        System.out.println(userDAO.getByPK(user2.getId()));
        user2.setEmail("test@test.ru2");
        System.out.println(userDAO.getByPK(user2.getId()));

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setId(user2);
        userInfo2.setFirstName("name");
        userInfo2.setSecondName("secondname");
        userInfo2.setLastName("lastname");
        userInfoDAO.save(userInfo2);
        System.out.println(userInfoDAO.getByPK(user2));

        user2.setEmail("test@test.ru3");
        System.out.println(userInfoDAO.getByPK(user2)); //TODO

//        User user2 = new User(UUID.randomUUID(), UUID.randomUUID().toString(), "User1", "test@test2.ru");
//        user.setUserInfo(extraUserInfo);
//        extraUserInfo.setId(user);
//        userInfoDAO.save(extraUserInfo);
//        userDAO.save(user);
//        System.out.println(userInfoDAO.getByPK(user)); //TODO будет ошибка
        user.setPassword("passwfgfdgdgdfgdcord");
        user.setUserInfo(new UserInfo());

//        System.out.println(userInfoDAO.getByPK(user));

//        userDAO.update(user);
//        user.setId(UUID.randomUUID().toString());

//        userDAO.update(user);
//        System.out.println(userDAO.getByPK(user.getId()));

//        User user2 = new User();
//        user2.setId(UUID.randomUUID());
//        user2.setUsername("User1");
//        user2.setEmail("test@test2.ru");
//        user2.setPassword("password");
//        user2.setRole(Role.ROLE_OPERATOR);
//        user2.setUserStatus(UserStatus.STATUS_ACTIVED);
//        user2.setUserInfo(extraUserInfo);
//        userDAO.save(user);

        Set<User> users = new HashSet<>();
        users.add(user);
        //users.add(user2);
        Organization organization = new Organization(1L, "ООО Организация 1");
        organization.setOrganizationStatus(OrganizationStatus.STATUS_CREATED);
        organization.setUsers(users);
        organizationDAO.save(organization);
        System.out.println(organizationDAO.getByPK(organization.getId()));
        organization.setUpdatedAt(LocalDateTime.now());
//
        System.out.println(organizationDAO.getByPK(organization.getId()));

        System.out.println(organizationDAO.getByUser(user));
        System.out.println(organizationDAO.getByUser(new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(),"Admin1", "test@test.ru")));
//
//        System.out.println(userInfoDAO.getAll());

    }
}
