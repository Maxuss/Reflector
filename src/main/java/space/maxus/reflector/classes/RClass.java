package space.maxus.reflector.classes;

import space.maxus.reflector.exceptions.ClassInitializationException;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RClass<TObject> {
    /**
     * Java alternative of the class
     */
    public final Class<TObject> java;

    public RClass(Class<TObject> java) {
        this.java = java;
    }

    /**
     * @return Superclass of the class
     */
    public RClass<? super TObject> superClass() {
        return new RClass<>(java.getSuperclass());
    }

    /**
     * @return Interfaces of the class
     */
    public List<RClass<?>> interfaces() {
        return Arrays.stream(java.getInterfaces()).map(RClass::new).collect(Collectors.toList());
    }

    /**
     * Grabs a field from the class
     * @param name Name of the field to access
     * @param <TValue> Return type of the field
     * @return RField instance of the field
     */
    public <TValue> RField<TObject, TValue> getField(String name) {
        return new RField<>(this, name);
    }

    /**
     * Tries to grab a field from the class
     * @param name Name of the field to access
     * @param <TValue> Return type of the field
     * @return Optional-encapsulated instance of the field
     */
    public <TValue> Optional<RField<TObject, TValue>> tryGetField(String name) {
        return Optional.ofNullable(getField(name));
    }

    /**
     * Tries to instantiate class
     * @return New instance of the class
     * @throws ClassInitializationException If java could not instantiate the class
     */
    public TObject newInstance() throws ClassInitializationException {
        try {
            return java.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ClassInitializationException("Could not initialize class!" + e.getMessage());
        }
    }
}
