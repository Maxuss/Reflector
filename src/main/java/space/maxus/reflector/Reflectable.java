package space.maxus.reflector;

import space.maxus.reflector.classes.RClass;

public interface Reflectable {
    /**
     * @return Owner RClass of the current reflectable
     */
    RClass<?> owner();
}
