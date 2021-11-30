package space.maxus.reflector.classes;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface RClass<TObject> {
    Class<TObject> java();
    RClass<TObject> superClass();
    List<RClass<TObject>> interfaces();

    default TObject newI() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return java().getDeclaredConstructor().newInstance();
    }
}
