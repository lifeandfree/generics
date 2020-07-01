package ru.innopolis.service.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.innopolis.service.UserService;
import ru.innopolis.service.UserServiceImpl;

import java.time.LocalDateTime;

/**
 * ContextRefreshListner.
 *
 * @author lifeandfree
 */
@Component
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LogManager.getLogger(ContextRefreshListener.class.getName());

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        log.info("ContextRefreshListener");
        userService.setTime(LocalDateTime.now());
    }
}
