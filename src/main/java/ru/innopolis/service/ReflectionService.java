package ru.innopolis.service;

import static ru.innopolis.service.provider.CrudTemplateProvider.UPDATEABLE;

import ru.innopolis.model.ExtraUserInfo;
import ru.innopolis.model.User;
import ru.innopolis.model.UserInfo;
import ru.innopolis.model.enums.Role;
import ru.innopolis.model.enums.UserStatus;
import ru.innopolis.service.provider.CrudTemplateProvider;

import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * ReflectionService.
 *
 * @author Ilya_Sukhachev
 */
public class ReflectionService {

    public static void main(String[] args) {

        CrudTemplateProvider<User, UUID> crudTemplateProvider = new CrudTemplateProvider<>();

        LinkedHashMap<String, String> columnMap= crudTemplateProvider.getColumnMap(User.class, UPDATEABLE);

        System.out.println(columnMap);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(UUID.randomUUID());
        userInfo.setFirstName("name");
        userInfo.setSecondName("secondname");
        userInfo.setLastName("lastname");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("Admin1");
        user.setEmail("test@test.ru");
        user.setPassword("password");
        user.setRole(Role.ROLE_ADMIN);
        user.setUserStatus(UserStatus.STATUS_ACTIVED);
        user.setUuid(UUID.randomUUID().toString());
        user.setUserInfo(userInfo);

        System.out.println(crudTemplateProvider.insert(user));
        System.out.println(crudTemplateProvider.select(UserInfo.class));

        System.out.println(crudTemplateProvider.getIdColumnMap(User.class, CrudTemplateProvider.ALL));
        System.out.println(crudTemplateProvider.select(User.class));

    }
}
