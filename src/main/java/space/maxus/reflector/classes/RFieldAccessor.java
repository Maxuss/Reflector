package space.maxus.reflector.classes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.reflector.Reflector;
import space.maxus.reflector.exceptions.FieldException;

import java.lang.reflect.Field;
import java.util.Optional;

@SuppressWarnings({"unused", "FieldCanBeLocal", "unchecked"})
public class RFieldAccessor<TValue, TOwner> implements RProperty {
    private final RClass<TOwner> rClass;
    private final TOwner inst;

    /**
     * Name of the field
     */
    public final String name;
    /**
     * Java alternative of the field
     */
    public final Field java;

    RFieldAccessor(@NotNull RClass<TOwner> clazz, TOwner obj, String name) throws FieldException {
        rClass = clazz;
        inst = obj;
        this.name = name;
        try {
            java = clazz.java.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new FieldException("Could not access field "+name+"!");
        }
    }

    /**
     * Attempts to get a value from field
     * @return Field value or null
     */
    @Nullable
    public TValue get() {
        try {
            return (TValue) java.get(inst);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /**
     * Attempts to set value to field
     *
     * @param value Field value to be set
     */
    void set(TValue value) {
        try {
            java.set(inst, value);
        } catch (IllegalAccessException ignored) {

        }
    }

    /**
     * Tries to get a value from field
     *
     * @return Optional-encapsulated value of field
     */
    @NotNull
    Optional<TValue> tryGet() {
        return Optional.ofNullable(get());
    }

    /**
     * Tries to set a value from field
     * @param value Field value to be set
     * @return Whether it was possible to set value of field
     */
    boolean trySet(@Nullable TValue value) {
        try {
            java.set(inst, value);
            return true;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    /**
     * @return Owner RClass of the current reflectable
     */
    @Override
    public RClass<?> owner() {
        return Reflector.classOf(inst.getClass());
    }

    /**
     * Checks if the property is accessible
     *
     * @return Whether it is possible to access the property
     */
    @Override
    public boolean canAccess() {
        return java.canAccess(inst);
    }

    /**
     * Sets the property to accessible or not
     *
     * @param accessible Whether the property will be accessible
     */
    @Override
    public void setAccessible(boolean accessible) {
        java.setAccessible(accessible);
    }
}