import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

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

        Iterator<Integer> iter = tree.iterator();
        Integer currentValue, nextValue;
        nextValue = iter.next();

        while (iter.hasNext()) {
            currentValue = nextValue;
            nextValue = iter.next();
            assertTrue( nextValue.compareTo(currentValue) > 0);
        }
    }
}