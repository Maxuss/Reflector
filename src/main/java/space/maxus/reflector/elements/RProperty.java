package space.maxus.reflector.elements;

import space.maxus.reflector.Reflectable;

public interface RProperty extends Reflectable {
    /**
     * Checks if the property is accessible
     * @return Whether it is possible to access the property
     */
    boolean canAccess();

    /**
     * Sets the property to accessible or not
     * @param accessible Whether the property will be accessible
     */
    void setAccessible(boolean accessible);
}
