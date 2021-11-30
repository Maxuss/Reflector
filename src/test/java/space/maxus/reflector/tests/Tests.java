package space.maxus.reflector.tests;

import org.junit.jupiter.api.Test;
import space.maxus.reflector.Reflector;
import space.maxus.reflector.classes.RClass;
import space.maxus.reflector.classes.RField;
import space.maxus.reflector.exceptions.ClassInitializationException;

import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class Tests {
    @Test
    public void test() throws ClassInitializationException {
        RClass<AccessMe> clazz = Reflector.find("space.maxus.reflector.tests.AccessMe");
        assert clazz != null;
        Optional<RField<AccessMe, String>> field = clazz.tryGetField("imNormal");
        var accessor = field.get().tryAccess().get();
        System.out.println(accessor.get());
        accessor.set("New Value");
        System.out.println(accessor.get());
    }
}
