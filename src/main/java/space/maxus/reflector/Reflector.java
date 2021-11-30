package space.maxus.reflector;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.reflector.classes.RClass;

@SuppressWarnings("unchecked")
public class Reflector {
    @Contract(value = "_ -> new", pure = true)
    public static <TClass> @NotNull RClass<TClass> classOf(Class<TClass> java) {
        return new RClass<>(java);
    }

    public static void createScope() {

    }

    public static <TObject> @Nullable RClass<TObject> find(String className) {
        try {
            return (RClass<TObject>) classOf(Class.forName(className));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
