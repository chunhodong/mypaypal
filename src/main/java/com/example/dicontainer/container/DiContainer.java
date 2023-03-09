package com.example.dicontainer.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DiContainer {

    private static final int CONST_SIZE = 1;
    private final Set<Object> beans;

    public DiContainer(final Set<Class<?>> classes) {
        this.beans = createBeans(classes);
    }

    private Set<Object> createBeans(final Set<Class<?>> classes) {
        Set<Object> beans = new HashSet<>();
        for (Class<?> aClass : classes) {
            beans.add(createInstance(aClass));
        }
        return beans;
    }

    private Object createInstance(final Class<?> aClass) {
        try {
            Constructor<?>[] constructors = aClass.getDeclaredConstructors();
            validateConstructorSize(constructors);
            Constructor<?> constructor = constructors[0];
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateConstructorSize(Constructor<?>[] constructors){
        if(Objects.isNull(constructors)){
            throw new NullPointerException();
        }
        if(constructors.length != CONST_SIZE){
            throw new IllegalArgumentException();
        }
    }

    public <T> T getBean(final Class<T> aClass) {
        return (T) beans.stream()
                .filter(bean -> aClass.isAssignableFrom(bean.getClass()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
