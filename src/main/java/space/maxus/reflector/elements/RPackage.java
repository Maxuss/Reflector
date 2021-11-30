package space.maxus.reflector.elements;

import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.HashSet;

@SuppressWarnings("deprecation")
public class RPackage {
    /**
     * Fully qualified name of the package
     */
    @NotNull
    public final String name;

    public RPackage(@NotNull String name) {
        this.name = name;
    }

    /**
     * Fetches all classes inside the package
     * @return all classes inside the package
     */
    public HashSet<Class<?>> fetch() {
        Reflections reflect = new Reflections(name, new SubTypesScanner(false));
        return new HashSet<>(reflect.getSubTypesOf(Object.class));
    }
}
