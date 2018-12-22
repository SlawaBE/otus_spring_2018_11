package ru.otus.spring01.shell;

import org.junit.jupiter.api.Test;
import ru.otus.spring01.service.TestingService;

import java.io.InputStream;
import java.io.OutputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by Stanislav on 22.12.2018
 */
class TestCommandTest {

    @Test
    public void testCommand() {
        TestingService testingService = mock(TestingService.class);
        TestCommand cmd = new TestCommand(testingService);
        cmd.test();
        verify(testingService).test(any(InputStream.class), any(OutputStream.class));
    }

}