package ru.innopolis.service.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.innopolis.service.UserService;

import java.time.LocalDateTime;

/**
 * ContextRefreshListner.
 *
 * @author lifeandfree
 */
@Component
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        System.out.println("info");
        userService.setTime(LocalDateTime.now());
    }
}
