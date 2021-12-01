package space.maxus.reflector.params;

import space.maxus.reflector.Reflector;
import space.maxus.reflector.elements.RClass;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public record RParameter<TValue>(TValue value) {
    /**
     * @return RClass of the stored value
     */
    public RClass<TValue> valueClass() {
        return (RClass<TValue>) Reflector.classOf(value.getClass());
    }

    /**
     * Collects this element and others into param collection
     * @param others other parameters to be added into collection
     * @return parameter collection of this anc other params
     */
    public ParameterCollection collect(RParameter<?>... others) {
        final var coll = new ParameterCollection();
        coll.add(this);
        coll.addAll(Arrays.stream(others).toList());
        return coll;
    }
}
