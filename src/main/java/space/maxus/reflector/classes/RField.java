package space.maxus.reflector.classes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface RField<TOwner, TReturn> extends RProperty {
    /**
     * @return RClass of the object value of field
     */
    @NotNull
    RClass<TOwner> objectClass();

    /**
     * @return RClass of the return value of field
     */
    @NotNull
    RClass<TReturn> valueClass();

    /**
     * Receives the accessor of the field or returns null
     * @param owner object to which the field belongs. Can be null if the field is static
     * @return Field accessor or null
     */
    @Nullable
    RFieldAccessor<TReturn> access(@Nullable TOwner owner);

    /**
     * Tries to receive the accessor of the field
     * @param owner object to which the field belongs. Can be null if the field is static
     * @return Optional-encapsulated field accessor
     */
    @NotNull
    Optional<RFieldAccessor<TReturn>> tryAccess(@Nullable TOwner owner);
}
