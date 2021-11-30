package space.maxus.reflector;

import space.maxus.reflector.elements.RClass;
import space.maxus.reflector.elements.RPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReflectorScope {
    private final HashMap<String, RClass<?>> included = new HashMap<>();

    public void include(String pkg) {
        var rpkg = new RPackage(pkg);
        var entries = rpkg
                .fetch().stream()
                .map(it -> Map.entry(it.getPackageName() + it.getName(), new RClass<>(it)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        included.putAll(entries);
    }
}
