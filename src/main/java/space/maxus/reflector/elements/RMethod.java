package space.maxus.reflector.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.reflector.PossibleStatic;
import space.maxus.reflector.Reflectable;
import space.maxus.reflector.exceptions.MethodException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;

public class RMethod<TOwner, TReturn> implements Reflectable, PossibleStatic {
    private final RClass<TOwner> owner;

    /**
     * Java mirror of the method
     */
    public final Method java;

    RMethod(RClass<TOwner> ownerClass, String name, Class<?>... params) throws MethodException {
        owner = ownerClass;
        try {
            java = ownerClass.java.getMethod(name, params);
        } catch (NoSuchMethodException e) {
            throw new MethodException("Could not find method "+name+"! "+e.getMessage());
        }
    }

    @Override
    public RClass<?> owner() {
        return owner;
    }

    @NotNull
    public RMethodAccessor<TOwner, TReturn> access(@Nullable TOwner owner) {
        return new RMethodAccessor<>(this.owner, java, owner);
    }

    /**
     * Checks whether the property is static
     *
     * @return Whether the property is static
     */
    @Override
    public boolean isStatic() {
        return Modifier.isStatic(java.getModifiers());
    }
}
