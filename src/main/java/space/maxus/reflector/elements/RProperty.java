package space.maxus.reflector.elements;

import space.maxus.reflector.PossibleStatic;
import space.maxus.reflector.Reflectable;

public interface RProperty extends Reflectable, PossibleStatic {
    /**
     * Checks if the property is accessible
     * @return Whether it is possible to access the property
     */
    boolean canAccess();

    /**
     * Checks whether the property is static
     * @return Whether it is possible to access the property without instance object
     */
    @Override
    boolean isStatic();

    /**
     * Sets the property to accessible or not
     * @param accessible Whether the property will be accessible
     */
    void setAccessible(boolean accessible);
}
