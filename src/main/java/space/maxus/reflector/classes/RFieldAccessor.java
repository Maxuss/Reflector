package space.maxus.reflector.classes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface RFieldAccessor<TValue> extends RProperty {
    @Nullable
    TValue get();
    void set(TValue value);

    @NotNull
    Optional<TValue> tryGet(@Nullable TValue or);
    @NotNull
    Optional<Boolean> trySet(@Nullable TValue value);
}