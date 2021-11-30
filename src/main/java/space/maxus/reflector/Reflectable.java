package space.maxus.reflector;

import space.maxus.reflector.elements.RClass;

public interface Reflectable {
    /**
     * @return Owner RClass of the current reflectable
     */
    RClass<?> owner();
}
