package com.example.dicontainer.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class DiContainer {

    private static final int CONST_SIZE = 1;
    private final Set<Object> beans = new HashSet<>();

    public DiContainer(Set<Class<?>> classes) {
        createBeans(classes);
    }

    private void createBeans(final Set<Class<?>> classes) {
        classes.stream().forEach(aClass -> beans.add(createInstance(aClass)));
    }

    private Object createInstance(final Class<?> aClass) {
        try {
            Constructor<?>[] constructors = aClass.getDeclaredConstructors();
            validateConstructorSize(constructors);
            return newInstance(constructors[0]);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Object newInstance(Constructor<?> constructor) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        constructor.setAccessible(true);
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int size = parameterTypes.length;
        if (parameterTypes == null || parameterTypes.length == 0) return constructor.newInstance();
        Object[] arguments = Arrays.stream(parameterTypes)
                .map(parameterType -> getBean(parameterType))
                .collect(Collectors.toList()).toArray();
        return constructor.newInstance(arguments);
    }

    private void validateConstructorSize(Constructor<?>[] constructors) {
        if (Objects.isNull(constructors)) {
            throw new NullPointerException();
        }
        if (constructors.length != CONST_SIZE) {
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
