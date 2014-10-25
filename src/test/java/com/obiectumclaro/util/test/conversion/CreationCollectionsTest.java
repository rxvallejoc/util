/**
 * 
 */
package com.obiectumclaro.util.test.conversion;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.obiectumclaro.util.conversion.CreationCollections;
import com.obiectumclaro.util.exception.ParsingException;

/**
 * @author fausto
 *
 */
public class CreationCollectionsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldCreateArrayListFromList() {
		try {
			List<String> list = (List<String>) CreationCollections.createCollection(List.class, String.class);
			assertTrue(list.getClass().getName().compareTo(ArrayList.class.getName()) == 0);
			assertTrue(list.isEmpty());
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void shouldCreateArrayListFromCollection() {
		try {
			List<String> list = (List<String>) CreationCollections.createCollection(List.class, String.class);
			assertTrue(list.getClass().getName().compareTo(ArrayList.class.getName()) == 0);
			assertTrue(list.isEmpty());
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void shouldCreateHashSetListFromSet() {
		try {
			Set<String> list = (Set<String>) CreationCollections.createCollection(Set.class, String.class);
			assertTrue(list.getClass().getName().compareTo(HashSet.class.getName()) == 0);
			assertTrue(list.isEmpty());
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = ClassCastException.class)
	public void shouldNotCreateHashSetListFromList() {
		try {
			Set<String> list = (Set<String>) CreationCollections.createCollection(List.class, String.class);
			fail(String.format("No deberia crear un set a partir de un list %", list.getClass().getName()));
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = ClassCastException.class)
	public void shouldNotCreateArrayListListFromSet() {
		try {
			List<String> list = (List<String>) CreationCollections.createCollection(Set.class, String.class);
			fail(String.format("No deberia crear un list a partir de un set %", list.getClass().getName()));
		} catch (ParsingException e) {
			fail(e.getMessage());
		}
	}
}
