package ua.edu.ucu.autocomplete;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import ua.edu.ucu.tries.RWayTrie;

public class PrefixMatchesAnotherTest {
    private PrefixMatches pm;

    @Before
    public void setUp() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
    }

    @Test
    public void testContains() {
        boolean resultTrue = pm.contains("abc");
        boolean resultFalse = pm.contains("haha");

        assertTrue(resultTrue);
        assertFalse(resultFalse);
    }

    @Test
    public void testDelete() {
        boolean resultTrue = pm.delete("abc");
        boolean resultFalse = pm.delete("haha");

        assertTrue(resultTrue);
        assertFalse(resultFalse);
    }

    @Test
    public void testSize() {
        assertEquals(5, pm.size());
        pm.delete("abcd");
        assertEquals(4, pm.size());
    }

    @Test
    public void testSameLoads() {
        String pref = "ab";

        PrefixMatches testPm = new PrefixMatches(new RWayTrie());
        testPm.load("abc abc", "abc", "abc", "ab");

        Iterable<String> result = testPm.wordsWithPrefix(pref);

        String[] expResult = {"abc"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConditionInWordsWithPrefix() {
        String pref = "a";
        pm.wordsWithPrefix(pref);
    }

    @Test
    public void testWordsWithPrefixWithTwo() {
        String pref = "abc";
        int k = 2;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }
}
