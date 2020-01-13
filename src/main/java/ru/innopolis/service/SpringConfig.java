package ru.innopolis.service;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.innopolis.dao.UserDAO;
import ru.innopolis.dao.UserInfoDAO;
import ru.innopolis.dao.list.UserDAOImpl;
import ru.innopolis.dao.list.UserInfoDAOImpl;
import ru.innopolis.model.UserInfo;
import ru.innopolis.service.processor.BeanPostProcessorImpl;

/**
 * SpringConfig.
 *
 * @author lifeandfree
 */
@Configuration
@ComponentScan("ru.innopolis")
public class SpringConfig {

    @Bean
    public UserInfoDAO getUserInfoDAO() {
        return new UserInfoDAOImpl(UserInfo.class);
    }

    @Bean
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Bean
    public UserDAO getMapUserDAO() {
        return new ru.innopolis.dao.map.UserDAOImpl();
    }

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessorImpl();
    }
}
