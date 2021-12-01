package space.maxus.reflector.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.reflector.params.ParameterCollection;
import space.maxus.reflector.params.RParameter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
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
     * @param args Arguments for method to be invoked with
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
     * Invokes the method
     * @param params Parameters for method to be invoked with
     * @return Return value of the method or null
     */
    @Nullable
    public TReturn invoke(ParameterCollection params) {
        try {
            return (TReturn) java.invoke(inst, params.stream().map(RParameter::value));
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    /**
     * Invokes the method
     * @param params Parameters for method to be invoked with
     * @return Return value of the method or null
     */
    @Nullable
    public TReturn invoke(RParameter<?>... params) {
        var list = Arrays.stream(params).toList();
        if(list.isEmpty())
            return invoke(new ParameterCollection());
        list.remove(params[0]);
        return invoke(params[0].collect(list.toArray(new RParameter<?>[0])));
    }

    /**
     * Invokes the method and tries to receive its value
     * @param args Arguments for method to be invoked with
     * @return Optional-encapsulated return value of the method
     */
    @NotNull
    public Optional<TReturn> tryInvoke(Object... args) {
        return Optional.ofNullable(invoke(args));
    }

    /**
     * Invokes the method and tries to receive its value
     * @param params Parameters for method to be invoked with
     * @return Optional-encapsulated return value of the method
     */
    @NotNull
    public Optional<TReturn> tryInvoke(ParameterCollection params) {
        return Optional.ofNullable(invoke(params));
    }

    /**
     * Invokes the method and tries to receive its value
     * @param params Parameters for method to be invoked with
     * @return Optional-encapsulated return value of the method
     */
    @NotNull
    public Optional<TReturn> tryInvoke(RParameter<?>... params) {
        return Optional.ofNullable(invoke(params));
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
        try {
            return java.canAccess(inst);
        } catch(Exception e) {
            return true;
        }
    }

    /**
     * Checks whether the property is static
     *
     * @return Whether it is possible to access the property without instance object
     */
    @Override
    public boolean isStatic() {
        return Modifier.isStatic(java.getModifiers());
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
