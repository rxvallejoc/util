/**
 * 2013
 * obiectumclaro
 */
package com.obiectumclaro.util.conversion;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.obiectumclaro.util.exception.ParsingException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class ConversionBasicDataType {

	@SuppressWarnings("unchecked")
	public static <T> T convertFromString(Class<T> type, String value, String datePattern) throws ParsingException {
		try {
			switch (type.getName()) {
			case "java.lang.String":
				return (T) value;
			case "java.lang.Integer":
				return (T) Integer.valueOf(value);
			case "java.lang.Float":
				return (T) Float.valueOf(value);
			case "java.lang.Double":
				return (T) Double.valueOf(value);
			case "java.math.BigDecimal":
				return (T) new BigDecimal(value);
			case "java.util.Date":
				return (T) new SimpleDateFormat(datePattern).parse(value);

			default:
				throw new IllegalArgumentException(String.format("No se encuentra un metodo de conversion de String a %s para el valor %s",
						type.getName(), value));
			}
		} catch (NumberFormatException | ParseException e) {
			throw new ParsingException(e);
		}
	}
}
