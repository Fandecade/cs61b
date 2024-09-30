package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer<T> {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        T ele = (T) arb.peek();
        assertEquals(1, ele);
        T ele2 = (T) arb.dequeue();
        assertEquals(1, ele2);
        assertEquals(3, arb.fillCount());
        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
        Iterator<T> it = arb.iterator();
        assertTrue(it.hasNext());


    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {

        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
