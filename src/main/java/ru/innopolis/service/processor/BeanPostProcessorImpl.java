package ru.innopolis.service.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.innopolis.service.UserService;
import ru.innopolis.service.UserServiceImpl;

import java.time.LocalDateTime;

/**
 * BeanPostProccessorImpl.
 *
 * @author lifeandfree
 */
public class BeanPostProcessorImpl implements BeanPostProcessor {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userServiceImpl")) {
            UserService userService = ((UserService) bean);
            LocalDateTime now = LocalDateTime.now();
            log.info(now);
            userService.setTime(now);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
