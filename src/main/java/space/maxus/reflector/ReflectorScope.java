package space.maxus.reflector;

import space.maxus.reflector.elements.RClass;
import space.maxus.reflector.elements.RPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReflectorScope {
    private final HashMap<String, RClass<?>> included = new HashMap<>();

    public void include(String pkg) {
        include(new RPackage(pkg));
    }

    public void include(RPackage rpkg) {
        var entries = rpkg
                .fetch().stream()
                .map(it -> Map.entry(it.getName(), new RClass<>(it)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        included.putAll(entries);
    }

    public RClass<?> get(String cls) {
        return included.get(cls);
    }
}
