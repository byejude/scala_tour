package javainterop;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import scala.*;
import scala.collection.mutable.LinkedHashMap;

/**
 * Author: Tulip
 * Date: 2020/1/9 21:20
 */
public class SMapTest extends org.scalatest.junit.JUnitSuite { // ➊
    static class Name {
        public String firstName;
        public String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    LinkedHashMap<Integer, Name> map;

    @Before
    public void setup() {
        map = new LinkedHashMap<Integer, Name>();
        map.update(1, new Name("Dean", "Wampler"));
    }

    @Test
    public void usingMapGetWithOptionName() { // ➋
        assertEquals(1, map.size());
        Option<Name> n1 = map.get(1); // Note: Option<Name>
        assertTrue(n1.isDefined());
        assertEquals("Dean", n1.get().firstName);
    }

    @Test
    public void usingMapGetWithOptionExistential() { // ➌
        assertEquals(1, map.size());
        Option<?> n1 = map.get(1); // Note: Option<?>
        assertTrue(n1.isDefined());
        assertEquals("Dean", ((Name) n1.get()).firstName);
    }
}
