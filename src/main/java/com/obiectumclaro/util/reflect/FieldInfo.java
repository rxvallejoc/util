/**
 * 29/01/2013
 *
 * Propiedad de obiectumclaro
 */
package com.obiectumclaro.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author Fausto De La Torre
 * 
 */
public class FieldInfo {

	public static final String CONSTRUCT_ERROR = "La propiedad %s de la clase %s debe tener getter y setter";
	public static final String SETTER_ERROR = "La propiedad %s de la clase %s debe tener setter valido";
	public static final String GETTER_ERROR = "La propiedad %s de la clase %s debe tener getter valido";

	private Field field;
	private Method getter;
	private Method setter;
	private Class<?> type;
	private Class<?> classType;

	public FieldInfo(String fieldName, Class<?> clazz) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			setFieldInfo(field);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(String.format("No se encuentra el field %s en la clase %s", fieldName, clazz.getName()));
		}
	}

	public FieldInfo(Field field) {
		setFieldInfo(field);
	}

	private void setFieldInfo(Field field) {
		this.field = field;
		this.type = field.getType();
		this.classType = field.getDeclaringClass();
		try {
			this.getter = classType.getMethod(getGetterMethod(field));
			this.setter = classType.getMethod(getSetterMethod(field), type);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(String.format(CONSTRUCT_ERROR, field.getName(), field.getDeclaringClass().getName()));
		}
	}

	private String getGetterMethod(Field f) {
		return String.format("get%s%s", f.getName().substring(0, 1).toUpperCase(), f.getName().substring(1));
	}

	private String getSetterMethod(Field f) {
		return String.format("set%s%s", f.getName().substring(0, 1).toUpperCase(), f.getName().substring(1));
	}

	public void invokeSetter(Object o, Object value) {
		try {
			this.setter.invoke(o, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(String.format(SETTER_ERROR, field.getName(), field.getDeclaringClass().getName()));
		}
	}

	public Object invokeGetter(Object o) {
		try {
			return this.getter.invoke(o);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(String.format(GETTER_ERROR, field.getName(), field.getDeclaringClass().getName()));
		}
	}

	public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
		return field.getAnnotation(annotationClass);
	}

	public Class<?> getFirstTypeArgument() {
		ParameterizedType type = (ParameterizedType) field.getGenericType();
		return (Class<?>) type.getActualTypeArguments()[0];
	}

	public Field getField() {
		return field;
	}

	public Method getGetter() {
		return getter;
	}

	public Method getSetter() {
		return setter;
	}

	public Class<?> getType() {
		return type;
	}

	public Class<?> getClassType() {
		return classType;
	}

	public String getName() {
		return field.getName();
	}

	public Field[] getChildrenFields() {
		return field.getType().getDeclaredFields();
	}

	public Field getChildField(String name) {
		try {
			return field.getType().getDeclaredField(name);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(String.format("No existe ninguna propiedad en la clase %s con el nombre %s",
					field.getType(), name));
		}
	}
}
