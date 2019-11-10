package com.sda.spring.mvc.hibernate;

import com.sda.spring.mvc.hibernate.config.PersistenceJpaConfig;
import com.sda.spring.mvc.hibernate.dao.UserDaoImpl;
import com.sda.spring.mvc.hibernate.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMvcHiberanteApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PersistenceJpaConfig.class);

        UserDaoImpl userDao = context.getBean("userDaoImpl", UserDaoImpl.class);

        User user = new User();
        user.setName("Ion Zapada");
        user.setEmail("ion.zapada@gmail.com");

        userDao.create(user);
    }
}
