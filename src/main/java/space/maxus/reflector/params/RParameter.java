package space.maxus.reflector.params;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.reflector.Reflector;
import space.maxus.reflector.elements.RClass;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class RParameter<TValue> {
    @Nullable
    private Class<TValue> javaAlternate = null;
    @NotNull
    private TValue value;

    public TValue value() {
        return value;
    }

    public @Nullable Class<TValue> getJavaAlternate() {
        return javaAlternate;
    }

    public RParameter(@NotNull TValue value) {
        this.value = value;
    }

    public RParameter(@NotNull TValue value, @Nullable Class<TValue> javaAlternate) {
        this.value = value;
        this.javaAlternate = javaAlternate;
    }

    public static final RParameter<?> INT = new RParameter<>(-1, int.class);
    public static final RParameter<?> SHORT = new RParameter<>((short) -1, short.class);
    public static final RParameter<?> DOUBLE = new RParameter<>(-1d, double.class);
    public static final RParameter<?> FLOAT = new RParameter<>(-1f, float.class);
    public static final RParameter<?> LONG = new RParameter<>(-1L, long.class);
    public static final RParameter<?> BYTE = new RParameter<>((byte) -1, byte.class);
    public static final RParameter<?> BOOL = new RParameter<>(false, boolean.class);

    public static final RParameter<?> CHAR = new RParameter<>('0', Character.class);
    public static final RParameter<?> STRING = new RParameter<>("", String.class);

    /**
     * Creates a parameter of array
     * @param arrayed Array value of parameters
     * @param <TArrayed> Type of value to be stored in parameter
     * @return New RParameter instance with this array data
     */
    @Contract("_ -> new")
    public static <TArrayed> @NotNull RParameter<TArrayed[]> ofArray(TArrayed... arrayed) {
        return new RParameter<>(arrayed);
    }

    /**
     * @return RClass of the stored value
     */
    public RClass<TValue> valueClass() {
        return javaAlternate != null ? Reflector.classOf(javaAlternate) : (RClass<TValue>) Reflector.classOf(value.getClass());
    }

    /**
     * @return Class of the stored value
     */
    public @NotNull Class<TValue> javaClass() {
        return javaAlternate != null ? javaAlternate : (Class<TValue>) value.getClass();
    }

    /**
     * Collects this element and others into param collection
     * @param others other parameters to be added into collection
     * @return parameter collection of this anc other params
     */
    public @NotNull ParameterCollection collect(RParameter<?>... others) {
        final var coll = new ParameterCollection();
        coll.add(this);
        coll.addAll(Arrays.stream(others).toList());
        return coll;
    }
}
