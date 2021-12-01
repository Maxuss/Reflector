package space.maxus.reflector.elements;

import space.maxus.reflector.Reflectable;
import space.maxus.reflector.exceptions.MethodException;

import java.lang.reflect.Method;

public class RMethod<TOwner, TReturn> implements Reflectable {
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
}
