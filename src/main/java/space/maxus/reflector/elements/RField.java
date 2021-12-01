package space.maxus.reflector.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.reflector.PossibleStatic;
import space.maxus.reflector.Reflectable;
import space.maxus.reflector.exceptions.ClassInitializationException;
import space.maxus.reflector.exceptions.FieldException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Optional;

public class RField<TOwner, TValue> implements Reflectable, PossibleStatic {
    @NotNull private final RClass<TOwner> objectClass;
    private final Field java;
    /**
     * Name of the field
     */
    @NotNull
    public final String name;

    RField(@NotNull RClass<TOwner> owner,  @NotNull String name) throws FieldException {
        objectClass = owner;
        this.name = name;
        try {
            java = objectClass.java.getField(name);
        } catch (NoSuchFieldException e) {
            throw new FieldException("Could not fidn field "+name+"! "+e.getMessage());
        }
    }

    /**
     * Receives the accessor of the field or returns null
     * @param owner object to which the field belongs. Can be null if the field is static
     * @return Field accessor or null
     */
    @Nullable
    public RFieldAccessor<TValue, TOwner> access(@Nullable TOwner owner) {
        try {
            return new RFieldAccessor<>(objectClass, owner, name);
        } catch (FieldException e) {
            return null;
        }
    }

    /**
     * Receives the accessor of the field or returns null
     * @return Field accessor or null
     */
    @Nullable
    public RFieldAccessor<TValue, TOwner> access() throws ClassInitializationException {
        return access(objectClass.construct());
    }

    /**
     * Tries to receive the accessor of the field
     * @param instance object to which the field belongs. Can be null if the field is static
     * @return Optional-encapsulated field accessor
     */
    @NotNull
    public Optional<RFieldAccessor<TValue, TOwner>> tryAccess(@Nullable TOwner instance) {
        return Optional.ofNullable(access(instance));
    }

    /**
     * Tries to receive the accessor of the field
     * @return Optional-encapsulated field accessor
     */
    @NotNull
    public Optional<RFieldAccessor<TValue, TOwner>> tryAccess() throws ClassInitializationException {
        return Optional.ofNullable(access(objectClass.construct()));
    }

    /**
     * @return Owner RClass of the current reflectable
     */
    @Override
    public RClass<?> owner() {
        return objectClass;
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
