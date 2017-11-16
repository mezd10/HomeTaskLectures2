import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinaryTreeIteratorTest {

    private BinaryTree<Integer> tree;

    private List<Integer> list;

    public BinaryTreeIteratorTest() {
        tree = new BinaryTree<>();
        list = Arrays.asList(3, 8, 99, 5, 6, 1, 2, 18, 20);
        tree.addAll(list);
    }

    @Test
    public void findNext() {

        Iterator<Integer> iterator = tree.iterator();

        assertEquals(1, (int) iterator.next());
        assertEquals(2, (int) iterator.next());
        assertEquals(3, (int) iterator.next());
        assertEquals(5, (int) iterator.next());
        assertEquals(6, (int) iterator.next());
        assertEquals(8, (int) iterator.next());
        assertEquals(18, (int) iterator.next());
        assertEquals(20, (int) iterator.next());
        assertEquals(99, (int) iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}