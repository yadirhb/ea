package edu.mum.cs544;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        // Exercise 1, part a
        // ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
        Greeting greeting = context.getBean("greeting", Greeting.class);
        greeting.sayHello();
    }
}
