package space.maxus.reflector;

import space.maxus.reflector.classes.RClass;

public class Reflector {
    public static <TClass> RClass<TClass> classOf(Class<TClass> java) {
        return new RClass<>(java);
    }

    public static void createScope() {

    }
}
