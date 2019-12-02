package edu.mum.main;


import edu.mum.domain.User;
import edu.mum.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    private static EntityManagerFactory emf;

    private static ApplicationContext context;
    private static UserService userService;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("context/applicationContext.xml");
        emf = (EntityManagerFactory) context.getBean("entityManagerFactory");

        userService = context.getBean(UserService.class);

        userService.save(new User("John", "Doe", "john.doe@mail.com"));

        List<User> users = userService.findAll();

        System.out.println("******* User ********");
        for (User user : users) {
            System.out.println("User Name: " + user.getFirstName() + " " + user.getLastName());
        }

        mergeUser();
    }

    private static void mergeUser() {
        User user = new User("New", "User", "user@mail.com");

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.println(1);
        entityManager.persist(user);
        System.out.println(2);
        // entityManager.getTransaction().commit();

        System.out.println(3);

        user.setEmail("user@gmail.com");

        System.out.println(4);
        user = entityManager.merge(user);
        System.out.println(5);

        entityManager.getTransaction().commit();

        System.out.println(6);
        entityManager.detach(user);

        System.out.println(7);

        user.setEmail("user@mum.edu");
        System.out.println(8);
        user = entityManager.merge(user);

        entityManager.close();
        emf.close();
    }

}