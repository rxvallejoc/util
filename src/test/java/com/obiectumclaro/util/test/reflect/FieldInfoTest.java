/**
 * 29/01/2013
 *
 * Propiedad de obiectumclaro
 */
package com.obiectumclaro.util.test.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.obiectumclaro.util.reflect.FieldInfo;

/**
 * @author Fausto De La Torre <fausto.delatorre@obiectumclaroec.com>
 *
 */
public class FieldInfoTest {

	private Clazz tc;

	@Before
	public void setUp() throws Exception {
		tc = new Clazz();
	}

	@Test
	public void shouldInvokeSetterGetter() throws NoSuchFieldException, SecurityException {
		FieldInfo fi = new FieldInfo(Clazz.class.getDeclaredField("a"));
		fi.invokeSetter(tc, "hola");
		String resultGetStr = (String) fi.invokeGetter(tc);
		assertEquals("hola", resultGetStr);

		fi = new FieldInfo(Clazz.class.getDeclaredField("b"));
		fi.invokeSetter(tc, 5L);
		Long resultGetLong = (Long) fi.invokeGetter(tc);
		assertEquals(new Long(5L), resultGetLong);

		fi = new FieldInfo(Clazz.class.getDeclaredField("c"));
		Subclazz setObject = new Subclazz();
		fi.invokeSetter(tc, setObject);
		Subclazz resultGet = (Subclazz) fi.invokeGetter(tc);
		assertEquals(setObject, resultGet);
	}

	@Test
	public void shouldNotInstantiateFieldInfo() throws NoSuchFieldException, SecurityException {
		try {
			new FieldInfo(Clazz.class.getDeclaredField("d"));
			fail("No se debe permitir crear un FieldInfo sin getter y setter bien definidos");
		} catch (RuntimeException e) {
			assertEquals(String.format(FieldInfo.CONSTRUCT_ERROR, "d", Clazz.class.getName()), e.getMessage());
		}
	}

	@Test
	public void shouldNotCastInvokeGetterResult() throws NoSuchFieldException, SecurityException {
		FieldInfo fi = new FieldInfo(Clazz.class.getDeclaredField("e"));
		try {
			fi.invokeSetter(tc, 2);
			Integer expected = (Integer) fi.invokeGetter(tc);
			fail(String.format("No se debe permitir invocar el getter de una propiedad si no esta bien definido el metodo", expected));
		} catch (ClassCastException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void shouldNotInvokeSetter() throws NoSuchFieldException, SecurityException {
		FieldInfo fi = new FieldInfo(Clazz.class.getDeclaredField("e"));
		try {
			fi.invokeSetter(tc, "error");
		} catch (RuntimeException e) {
			assertEquals(String.format(FieldInfo.SETTER_ERROR, "e", Clazz.class.getName()), e.getMessage());
		}
	}

	@Test
	public void shouldAcceptInvokeSetterGetterOverload() throws NoSuchFieldException, SecurityException {
		FieldInfo fi = new FieldInfo(Clazz.class.getDeclaredField("f"));
		fi.invokeSetter(tc, new Float(5));
		Float resultGet = (Float) fi.invokeGetter(tc);
		assertEquals(new Float(5), resultGet);
	}

}
