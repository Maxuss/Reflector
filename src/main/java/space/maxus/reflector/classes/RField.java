package space.maxus.reflector.classes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.reflector.Reflectable;
import space.maxus.reflector.exceptions.FieldException;

import java.util.Optional;

public class RField<TOwner, TReturn> implements Reflectable {
    @NotNull private final RClass<TOwner> objectClass;

    /**
     * Name of the field
     */
    @NotNull
    public final String name;

    RField(@NotNull RClass<TOwner> owner,  @NotNull String name) {
        objectClass = owner;
        this.name = name;
    }

    /**
     * Receives the accessor of the field or returns null
     * @param owner object to which the field belongs. Can be null if the field is static
     * @return Field accessor or null
     */
    @Nullable
    public RFieldAccessor<TReturn, TOwner> access(@Nullable TOwner owner) {
        try {
            return new RFieldAccessor<>(objectClass, owner, name);
        } catch (FieldException e) {
            return null;
        }
    }

    /**
     * Tries to receive the accessor of the field
     * @param owner object to which the field belongs. Can be null if the field is static
     * @return Optional-encapsulated field accessor
     */
    @NotNull
    public Optional<RFieldAccessor<TReturn, TOwner>> tryAccess(@Nullable TOwner owner) {
        return Optional.ofNullable(access(owner));
    }

    /**
     * @return Owner RClass of the current reflectable
     */
    @Override
    public RClass<?> owner() {
        return objectClass;
    }
}
