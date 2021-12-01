package space.maxus.reflector.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class RMethodAccessor<TOwner, TReturn> implements RProperty {
    @NotNull
    private final RClass<TOwner> owner;
    @NotNull
    private final Method java;
    @Nullable
    private final TOwner inst;

    RMethodAccessor(@NotNull RClass<TOwner> owner, @NotNull Method java, @Nullable TOwner obj) {
        this.owner = owner;
        this.java = java;
        this.inst = obj;
    }

    /**
     * Invokes the method
     * @param args Arguments for argument to be invoked with
     * @return Return value of the method or null
     */
    @Nullable
    public TReturn invoke(Object... args) {
        try {
            return (TReturn) java.invoke(inst, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    /**
     * Invokes the method and tries to receive its value
     * @param args Arguments for argument to be invoked with
     * @return Optional-encapsulated return value of the method
     */
    @NotNull
    public Optional<TReturn> tryInvoke(Object... args) {
        return Optional.ofNullable(invoke(args));
    }

    /**
     * @return Owner RClass of the current reflectable
     */
    @Override
    public RClass<TOwner> owner() {
        return owner;
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
     * Checks whether the property is static
     *
     * @return Whether it is possible to access the property without instance object
     */
    @Override
    public boolean isStatic() {
        return java.canAccess(null);
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
