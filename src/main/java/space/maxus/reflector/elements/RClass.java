package space.maxus.reflector.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.reflector.exceptions.FieldException;
import space.maxus.reflector.exceptions.MethodException;
import space.maxus.reflector.params.ParameterCollection;
import space.maxus.reflector.params.RParameter;

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
    @NotNull
    public List<RClass<?>> interfaces() {
        return Arrays.stream(java.getInterfaces()).map(RClass::new).collect(Collectors.toList());
    }

    /**
     * Grabs a field from the class
     * @param name Name of the field to access
     * @param <TValue> Return type of the field
     * @return RField instance of the field
     */
    @Nullable
    public <TValue> RField<TObject, TValue> getField(String name) {
        try {
            return new RField<>(this, name);
        } catch (FieldException e) {
            return null;
        }
    }

    /**
     * Grabs a method from the class
     * @param name Name of the method to access
     * @param params Parameters of the method
     * @param <TValue> Return type of the method
     * @return RMethod instance of the method
     */
    @Nullable
    public <TValue> RMethod<TObject, TValue> getMethod(String name, ParameterCollection params) {
        try {
            return new RMethod<>(this, name, Arrays.stream(params.unwrap()).map(RParameter::javaClass).toList().toArray(new Class<?>[0]));
        } catch (MethodException e) {
            return null;
        }
    }

    /**
     * Attempts to grab a method from the class
     * @param name Name of the method to access
     * @param params Parameters of the method
     * @param <TValue> Return type of the method
     * @return {@link java.util.Optional<TValue>}-encapsulated RMethod instance of the method
     */
    @NotNull
    public <TValue> Optional<RMethod<TObject, TValue>> tryGetMethod(String name, ParameterCollection params) {
        return Optional.ofNullable(getMethod(name, params));
    }

    /**
     * Tries to grab a field from the class
     * @param name Name of the field to access
     * @param <TValue> Return type of the field
     * @return Optional-encapsulated instance of the field
     */
    @NotNull
    public <TValue> Optional<RField<TObject, TValue>> tryGetField(String name) {
        return Optional.ofNullable(getField(name));
    }

    /**
     * Tries to instantiate class
     * @return New instance of the class
     */
    @Nullable
    public TObject construct() {
        try {
            return java.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    /**
     * Tries to instantiate class
     * @return Optional-encapsulated instance of the class
     */
    @NotNull
    public Optional<TObject> tryConstruct() {
        return Optional.ofNullable(construct());
    }
}
