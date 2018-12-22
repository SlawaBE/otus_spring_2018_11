package ru.otus.spring01.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring01.service.TestingService;

/**
 * Created by Stanislav on 22.12.2018
 */
@ShellComponent
public class TestCommand {

    private final TestingService testingService;

    @Autowired
    public TestCommand(TestingService testingService) {
        this.testingService = testingService;
    }

    @ShellMethod("testing service")
    public String test() {
        testingService.test(System.in, System.out);
        return "Bye";
    }
}
