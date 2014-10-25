/**
 * 
 */
package com.obiectumclaro.util.conversion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.obiectumclaro.util.exception.ParsingException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class CreationCollections {

	public static <T> Collection<T> createCollection(Class<?> type, Class<T> classElements) throws ParsingException {
		String className = type.getName();

		switch (className) {
		case "java.util.Collection":
			return new ArrayList<T>();
		case "java.util.List":
			return new ArrayList<T>();
		case "java.util.Set":
			return new HashSet<T>();
		default:
			throw new IllegalArgumentException(String.format("No se encuentra un metodo para crear una coleccion a partir de %s", type.getName()));
		}
	}
}
