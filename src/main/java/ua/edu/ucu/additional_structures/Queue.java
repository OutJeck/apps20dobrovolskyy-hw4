package ua.edu.ucu.additional_structures;

public class Queue {
    private ImmutableLinkedList queue;

    public Queue() {
        queue = new ImmutableLinkedList();
    }

    public Object peek() {
        return queue.getFirst();
    }

    public Object dequeue() {
        Object obj = this.peek();
        queue = queue.removeFirst();
        return obj;
    }

    public void enqueue(Object e) {
        queue = queue.addLast(e);
    }

    public int size() {
        return this.queue.size();
    }
}
