package ru.innopolis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * MainService.
 *
 * @author Ilya_Sukhachev
 */
@Component
public class MainService {

//    private static UserDAO userDAO = new UserDAOImpl();
//    private static OrganizationDAO organizationDAO = new OrganizationDAOImpl();
    private static UserInfoDAO userInfoDAO;

    public static void main(String[] args) {

        ApplicationContext context =
                     new AnnotationConfigApplicationContext(SpringConfig.class);

       UserService userService = (UserService) context.getBean("userServiceImpl");
        userInfoDAO = (UserInfoDAO) context.getBean("getUserInfoDAO");

        UserInfo userInfo = new UserInfo();
        userInfo.setId(UUID.randomUUID());
        userInfo.setFirstName("name");
        userInfo.setSecondName("secondname");
        userInfo.setLastName("lastname");
//        userInfoDAO.save(userInfo);

//        ExtraUserInfo extraUserInfo = new ExtraUserInfo();
//        extraUserInfo.setAddress("Address2");
//        extraUserInfo.setId(UUID.randomUUID());
//        extraUserInfo.setFirstName("name2");
//        extraUserInfo.setSecondName("secondname2");
//        extraUserInfo.setLastName("lastname2");
//        userInfoDAO.save(extraUserInfo);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("Admin1");
        user.setEmail("test@test.ru");
        user.setPassword("password");
        user.setRole(Role.ROLE_ADMIN);
        user.setUserStatus(UserStatus.STATUS_ACTIVED);
        user.setUuid(UUID.randomUUID().toString());
        user.setUserInfo(userInfo);
//        userDAO.save(user);

//        ((ConfigurableApplicationContext) context).refresh();
        userService.addUser(user);
        userService.addUser(user);
//        ((ConfigurableApplicationContext) context).refresh();
//        userService.addUser(user);
//        User user2 = new User();
//        user2.setId(UUID.randomUUID());
//        user2.setUsername("User1");
//        user2.setEmail("test@test2.ru");
//        user2.setPassword("password");
//        user2.setRole(Role.ROLE_OPERATOR);
//        user2.setUserStatus(UserStatus.STATUS_ACTIVED);
//        user2.setUuid(UUID.randomUUID().toString());
//        user2.setUserInfo(extraUserInfo);
//        userDAO.save(user);

//        Set<User> users = new HashSet<>();
//        users.add(user);
//        //users.add(user2);
//        Organization organization = new Organization();
//        organization.setId(1L);
//        organization.setOrganizationName("ООО Организация 1");
//        organization.setOrganizationStatus(OrganizationStatus.STATUS_CREATED);
//        organization.setUsers(users);
//        organizationDAO.save(organization);
//
//        System.out.println(organizationDAO.getByUser(user));
//        System.out.println(organizationDAO.getByUser(new User()));
//        System.out.println(userDAO.emailExist("test@tes.ru"));
//        System.out.println(userDAO.emailExist("test@test.ru"));
//
//        System.out.println(userInfoDAO.getAll());

    }
}
