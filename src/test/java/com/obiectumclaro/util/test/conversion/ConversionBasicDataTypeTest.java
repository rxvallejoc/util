package com.obiectumclaro.util.test.conversion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.obiectumclaro.util.conversion.ConversionBasicDataType;
import com.obiectumclaro.util.exception.ParsingException;

/**
 * @author Fausto De La Torre
 *
 */
public class ConversionBasicDataTypeTest {

	@Test
	public void shouldConvertString() {
		try {
			String result = ConversionBasicDataType.convertFromString(String.class, "valor", null);
			assertEquals(result, "valor");
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void shouldConvertInteger() {
		try {
			Integer result = ConversionBasicDataType.convertFromString(Integer.class, "5", null);
			assertEquals(result, new Integer(5));
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void shouldConvertFloat() {
		try {
			Float result = ConversionBasicDataType.convertFromString(Float.class, "9.6", null);
			assertEquals(result, new Float(9.6));
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void shouldConvertDouble() {
		try {
			Double result = ConversionBasicDataType.convertFromString(Double.class, "9.6", null);
			assertEquals(result, new Double(9.6));
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void shouldConvertBigDecimal() {
		try {
			BigDecimal result = ConversionBasicDataType.convertFromString(BigDecimal.class, "9.6", null);
			assertEquals(result, new BigDecimal("9.6"));
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void shouldConvertDate() {
		try {
			Date result = ConversionBasicDataType.convertFromString(Date.class, "15/01/2013", "dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(result);
			assertEquals(15, c.get(Calendar.DATE));
			assertEquals(0, c.get(Calendar.MONTH));
			assertEquals(2013, c.get(Calendar.YEAR));
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAllowArgument() {
		try {
			Character result = ConversionBasicDataType.convertFromString(Character.class, "15/01/2013", "dd/MM/yyyy");
			fail(String.format("No debe convertir nada que no exista dentro de las opciones %s", result));
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = ParsingException.class)
	public void shouldNotConvertInteger() throws ParsingException {
		Integer result = ConversionBasicDataType.convertFromString(Integer.class, "5.9", null);
		fail(String.format("No debe convertir un decimal [%s] a entero [%s]", "5.9", result));
	}

	@Test(expected = ParsingException.class)
	public void shouldNotConvertNumber() throws ParsingException {
		BigDecimal result = ConversionBasicDataType.convertFromString(BigDecimal.class, "5.9aa", null);
		fail(String.format("No debe convertir caracteres [%s] a numeros [%s]", "5.9aa", result));
	}

	@Test(expected = ParsingException.class)
	public void shouldNotConvertDate() throws ParsingException {
		Date result = ConversionBasicDataType.convertFromString(Date.class, "aas/05/2013", "dd/MM/yyyy");
		fail(String.format("No debe convertir una fecha [%s] con un formato invalido [%s]: fecha Generada [%s]", "15/05/2013", "MM/dd/yyyy", result));
	}

}
