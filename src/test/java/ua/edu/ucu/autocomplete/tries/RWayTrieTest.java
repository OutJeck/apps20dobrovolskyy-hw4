package ua.edu.ucu.autocomplete.tries;

import org.junit.Test;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Tuple;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class RWayTrieTest {

    @Test
    public void testContains(){
        RWayTrie tree = new RWayTrie();
        tree.add(new Tuple("abc", 3));
        tree.add(new Tuple("abcd", 4));
        tree.add(new Tuple("abcde", 4));

        boolean resultTrue = tree.contains("abc");
        boolean resultFalse = tree.contains("haha");

        assertTrue(resultTrue);
        assertFalse(resultFalse);

    }

    @Test
    public void testDelete(){
        RWayTrie tree = new RWayTrie();
        tree.add(new Tuple("abc", 3));
        tree.add(new Tuple("abcd", 4));
        tree.add(new Tuple("abcde", 5));

        tree.delete("abcd");
        boolean actualResult = tree.contains("abcd");

        assertFalse(actualResult);
    }

    @Test
    public void testSize() {
        RWayTrie tree = new RWayTrie();
        tree.add(new Tuple("abc", 3));
        tree.add(new Tuple("abcd", 4));
        tree.add(new Tuple("abcde", 5));

        int expectedSize = 3;
        int actualSize = tree.size();

        assertEquals(actualSize, expectedSize);

        tree.delete("abc");

        int actualSizeSecondTest = tree.size();

        assertEquals(expectedSize - 1, actualSizeSecondTest);
    }

    @Test
    public void testWords() {
        RWayTrie tree = new RWayTrie();
        String[] words = {"abc", "abce", "abcd", "abcde", "abcdef"};
        for (String word: words) {
            tree.add(new Tuple(word, word.length()));
        }

        String[] expectedResult = {"abc", "abce", "abcd", "abcde", "abcdef"};
        Iterable<String> actualResult = tree.words();

        assertThat(actualResult, containsInAnyOrder(expectedResult));
    }

}
