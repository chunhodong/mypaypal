package com.example.dicontainer.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DiContainer {

    private static final int CONST_SIZE = 1;
    private final Set<Object> beans = new HashSet<>();

    public DiContainer(Set<Class<?>> classes) {
        createBeans(classes);
    }

    private void createBeans(final Set<Class<?>> classes) {
        validateClasses(classes);
        Class<?>[] classesWithNoArgs = getClasses(classes,paramSize -> paramSize == 0);
        Class<?>[] classesWithArgs = getClasses(classes,paramSize -> paramSize > 0);
        Arrays.stream(classesWithNoArgs).forEach(aClass -> beans.add(createInstance(aClass)));
        Arrays.stream(classesWithArgs).forEach(aClass -> beans.add(createInstance(aClass)));
    }

    private Class<?>[] getClasses(Set<Class<?>> classes, Predicate<Integer> predicate) {
        return classes.stream()
                .filter(aClass -> predicate.test(aClass.getDeclaredConstructors()[0].getParameterTypes().length))
                .collect(Collectors.toList()).toArray(Class[]::new);
    }

    private void validateClasses(Set<Class<?>> classes) {
        classes.stream().forEach(aClass -> validateConstructor(aClass.getDeclaredConstructors()));
    }

    private Object createInstance(final Class<?> aClass) {
        try {
            return newInstance(aClass.getDeclaredConstructors()[0]);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Object newInstance(Constructor<?> constructor) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        constructor.setAccessible(true);
        Class<?>[] parameterTypes = constructor.getParameterTypes();

        if (parameterTypes == null || parameterTypes.length == 0) return constructor.newInstance();
        Object[] arguments = Arrays.stream(parameterTypes)
                .map(parameterType -> getBean(parameterType))
                .collect(Collectors.toList()).toArray();
        return constructor.newInstance(arguments);
    }

    private void validateConstructor(Constructor<?>[] constructors) {
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
