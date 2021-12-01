package space.maxus.reflector.tests;

import org.junit.jupiter.api.Test;
import space.maxus.reflector.Reflector;

public class Tests {
    @Test
    public void test() {
        var clazz = Reflector.find("space.maxus.reflector.tests.AccessMe");
        assert clazz != null;
        var inst = clazz.tryConstruct().orElseThrow();

    }
}
