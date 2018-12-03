package ru.otus.spring01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring01.service.TestingService;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TestingService testingService = (TestingService) context.getBean("testingService");
        testingService.test(System.in, System.out);
    }
}
