package ua.edu.ucu.autocomplete.queue;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.queue.Queue;

import static org.junit.Assert.*;

public class QueueTest {
    Queue queue;

    @Before
    public void setUP() {
        this.queue = new Queue();

        queue.enqueue(1);
        queue.enqueue('2');
        queue.enqueue(3.0);
        queue.enqueue("four");
    }
    
    @Test
    public void testPeak() {
        assertEquals(queue.peek(), 1);
    }

    @Test
    public void testDequeue() {
        assertEquals(queue.dequeue(), 1);
        assertEquals(queue.dequeue(), '2');
        assertEquals(queue.dequeue(), 3.0);
        assertEquals(queue.dequeue(), "four");
    }
    
}
