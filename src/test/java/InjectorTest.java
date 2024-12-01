import org.example.Injector;
import org.example.SomeBean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class InjectorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testInjectorWithSomeImpl() {
        SomeBean sb = new Injector().inject(new SomeBean());
        sb.foo();
        assertEquals("AC", outContent.toString().trim());
    }

    @Test
    public void testInjectorWithOtherImpl() {
        // сначала нужно изменить в config.properties
        // org.example.SomeInterface=org.example.SomeImpl
        // на
        // org.example.SomeInterface=org.example.OtherImpl

        SomeBean sb = new Injector().inject(new SomeBean());
        sb.foo();
        assertEquals("BC", outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
