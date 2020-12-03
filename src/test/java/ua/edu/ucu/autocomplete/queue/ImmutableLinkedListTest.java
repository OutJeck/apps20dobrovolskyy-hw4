package ua.edu.ucu.autocomplete.queue;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.queue.ImmutableLinkedList;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    ImmutableLinkedList linkedList;
    ImmutableLinkedList emptyList;

    @Before
    public void setUp() {
        this.linkedList = new ImmutableLinkedList(new Object[]{1, 2.1, "string"});

        this.emptyList = new ImmutableLinkedList();
    }
    
    @Test
    public void testAddObject() {
        ImmutableLinkedList newLinkedList = (ImmutableLinkedList) linkedList.add(6);
        ImmutableLinkedList newEmptyList = (ImmutableLinkedList) emptyList.add(6);

        assertEquals(newLinkedList.toString(), "[1, 2.1, string, 6]");
        assertEquals(newEmptyList.toString(), "[6]");

    }

    @Test
    public void testAddObjectByIndex() {
        ImmutableLinkedList newLinkedList = (ImmutableLinkedList) linkedList.add(1, 6);
        ImmutableLinkedList newEmptyList = (ImmutableLinkedList) emptyList.add(0, 6);

        assertEquals(newLinkedList.toString(), "[1, 6, 2.1, string]");
        assertEquals(newEmptyList.toString(), "[6]");

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddObjectByIndexError() {
        linkedList.add(8, "haha");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddObjectByIndexMinusError() {
        linkedList.add(-8, "haha");
    }

    @Test
    public void testAddAllObjects() {
        ImmutableLinkedList newLinkedList = (ImmutableLinkedList) linkedList.addAll(new Object[]{6, 'a'});
        ImmutableLinkedList newEmptyList = (ImmutableLinkedList) emptyList.addAll(new Object[]{6, 'a'});

        assertEquals(newLinkedList.toString(), "[1, 2.1, string, 6, a]");
        assertEquals(newEmptyList.toString(), "[6, a]");
    }

    @Test
    public void testAddAllObjectsByIndex() {
        ImmutableLinkedList newLinkedList = (ImmutableLinkedList) linkedList.addAll(1, new Object[]{6, 'a'});
        ImmutableLinkedList newEmptyList = (ImmutableLinkedList) emptyList.addAll(0, new Object[]{6, 'a'});

        assertEquals(newLinkedList.toString(), "[1, 6, a, 2.1, string]");
        assertEquals(newEmptyList.toString(), "[6, a]");

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllObjectsByIndexError() {
        linkedList.addAll(8, new Object[]{"haha"});
    }

    @Test
    public void testGetObject() {
        Object newObj = linkedList.get(2);

        assertEquals(newObj, "string");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetObjectError() {
        linkedList.get(8);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetObjectMinusError() {
        linkedList.get(-8);
    }

    @Test
    public void testRemoveObject() {
        ImmutableLinkedList newLinkedList = (ImmutableLinkedList) linkedList.remove(1);

        assertEquals(newLinkedList.toString(), "[1, string]");

    }

    @Test
    public void testRemoveObjectIndexZero() {
        ImmutableLinkedList newLinkedList = (ImmutableLinkedList) linkedList.remove(0);

        assertEquals(newLinkedList.toString(), "[2.1, string]");

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveObjectError() {
        linkedList.remove(6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveObjectMinusError() {
        linkedList.remove(-6);
    }

    @Test
    public void testSetObject() {
        ImmutableLinkedList newLinkedList = (ImmutableLinkedList) linkedList.set(1, "2.2");
        assertEquals(newLinkedList.toString(), "[1, 2.2, string]");

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetObjectMinusError() {
        linkedList.set(-5, "2.2");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetObjectError() {
        linkedList.set(5, "2.2");
    }

    @Test
    public void testIndexOfObject() {
        int index = linkedList.indexOf(2.1);
        assertEquals(index, 1);

    }

    @Test
    public void testIndexOfObjectMinusOne() {
        int index1 = linkedList.indexOf("mama");
        int index2 = emptyList.indexOf("haha");
        assertEquals(index1, -1);
        assertEquals(index2, -1);

    }

    @Test
    public void testSize() {
        int size1 = linkedList.size();
        int size2 = emptyList.size();

        assertEquals(size1, 3);
        assertEquals(size2, 0);

    }

    @Test
    public void testClear() {
        ImmutableLinkedList newLinkedList = (ImmutableLinkedList) linkedList.clear();
        int size = newLinkedList.size();
        assertEquals(size, 0);
    }

    @Test
    public void testIsEmpty() {
        boolean newLinkedList = linkedList.isEmpty();

        assertFalse(newLinkedList);
    }

    @Test
    public void testIsEmptyTrue() {
        boolean newEmptyList = emptyList.isEmpty();
        assertTrue(newEmptyList);
    }

    @Test
    public void testToArray() {
        Object[] newArray = linkedList.toArray();
        assertArrayEquals(newArray, new Object[]{1, 2.1, "string"});
    }

    @Test
    public void testAddFirst() {
        ImmutableLinkedList newLinkedList = linkedList.addFirst("smth");
        assertEquals(newLinkedList.toString(), "[smth, 1, 2.1, string]");
    }

    @Test
    public void testAddLast() {
        ImmutableLinkedList newLinkedList = linkedList.addLast("smth");
        assertEquals(newLinkedList.toString(), "[1, 2.1, string, smth]");
    }

    @Test
    public void testGetFirst() {
        assertEquals(linkedList.getFirst(), 1);
    }

    @Test
    public void testGetLast() {
        assertEquals(linkedList.getLast(), "string");
    }

    @Test
    public void testRemoveFirst() {
        ImmutableLinkedList newLinkedList = linkedList.removeFirst();
        assertEquals(newLinkedList.toString(), "[2.1, string]");
    }

    @Test
    public void testRemoveLast() {
        ImmutableLinkedList newLinkedList = linkedList.removeLast();
        assertEquals(newLinkedList.toString(), "[1, 2.1]");
    }

}
