package ua.edu.ucu.additional_structures;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private int size;

    public static class Node {
        private final Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
            this.next = null;
        }

        public Node() {
            this.data = null;
            this.next = null;
        }

    }

    public ImmutableLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public ImmutableLinkedList(Node node) {
        this.head = node;
    }

    public ImmutableLinkedList(Object[] objects) {
        if (objects.length == 0) {
            this.head = new Node();
        } else {
            this.head = new Node(objects[0]);

            Node forAwhile = this.head;
            for (int i = 1; i < objects.length; i++) {
                forAwhile.next = new Node(objects[i]);
                forAwhile = forAwhile.next;



            }
        }
        this.size = objects.length;
    }

    @Override
    public ImmutableList add(Object e) {
        return add(this.size, e);
    }

    private ImmutableLinkedList cloneList(ImmutableLinkedList initial) {
        if (initial.head == null) {
            return new ImmutableLinkedList();

        } else {
            ImmutableLinkedList newList = new ImmutableLinkedList();

            newList.head = new Node(initial.head.data);
            newList.size = size;

            Node linkedOriginal = initial.head.next;
            Node linkedNew = newList.head;

            while (linkedOriginal != null) {
                linkedNew.next = new Node(linkedOriginal.data);
                linkedNew = linkedNew.next;
                linkedOriginal = linkedOriginal.next;
            }
            return newList;

        }

    }

//    @Override
    public ImmutableList add(int index, Object e) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        ImmutableLinkedList newList = cloneList(this);
        Node newNode = new Node(e);
        Node headNode = newList.head;

        if (headNode == null) {
            newList.head = new Node(e);
        } else if (index == 0) {

            newNode.next = headNode;
            ImmutableLinkedList newNewList = new ImmutableLinkedList(newNode);
            newNewList.size = size + 1;
            return newNewList;

        } else {

            int counter = 0;
            while (headNode != null) {
                if (counter  == index - 1) {
                    Node nextHeadNode = headNode.next;
                    headNode.next = newNode;
                    headNode.next.next = nextHeadNode;
                }
                headNode = headNode.next;
                counter++;
            }
        }

        newList.size++;
        return newList;
    }

    @Override

    public ImmutableList addAll(Object[] c) {
        return addAll(this.size, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        ImmutableList newList = cloneList(this);
        for (int i = 0; i < c.length; i++) {
            newList = newList.add(index + i, c[i]);
        }

        return newList;
    }

    @Override
    public Object get(int index) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        ImmutableLinkedList newList = cloneList(this);

        int counter = 0;
        while (counter != index) {
            newList.head = newList.head.next;
            counter++;
        }
        return newList.head.data;
    }

    @Override
    public ImmutableList remove(int index) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Object[] newList = new Object[this.size - 1];
        Node currentNode = this.head;

        int i = 0;
        int j = 0;
        while (currentNode != null) {
            if (i != index) {
                newList[j] = currentNode.data;
                j++;
            }
            currentNode = currentNode.next;
            i++;
        }
        return new ImmutableLinkedList(newList);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Object[] newList = new Object[this.size];
        Node currentNode = this.head;

        int counter = 0;
        while (currentNode != null) {
            if (counter != index) {
                newList[counter] = currentNode.data;
            } else {
                newList[counter] = e;
            }
            currentNode = currentNode.next;
            counter++;
        }
        return new ImmutableLinkedList(newList);
    }

    @Override
    public int indexOf(Object e) {
        ImmutableLinkedList newList = cloneList(this);

        int counter = 0;
        while (counter < this.size) {
            if (newList.head.data.equals(e)) {
                return counter;
            }
            newList.head = newList.head.next;
            counter++;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public Object[] toArray() {
        ImmutableLinkedList newList = cloneList(this);
        Object[] outputArr = new Object[size];

        int i = 0;
        while (newList.head != null) {
            outputArr[i] = newList.head.data;
            newList.head = newList.head.next;
            ++i;
        }
        return outputArr;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(e);
    }

    public Object getFirst() {
        return get(0);
    }

    public Object getLast() {
        return get(this.size - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(this.size - 1);
    }

}
