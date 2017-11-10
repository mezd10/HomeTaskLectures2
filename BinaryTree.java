import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove(Object o) {

        @SuppressWarnings("unchecked")
        Node<T> treeEl = find((T) o);

        if (treeEl.left == null && treeEl.right == null) {

            del(treeEl, null);
        }

        if (treeEl.right == null && treeEl.left != null) {

            del(treeEl, treeEl.left);
        }

        if (treeEl.left == null && treeEl.right != null) {

            del(treeEl, treeEl.right);
        }

        if (treeEl.right != null && treeEl.left != null) {

            Node<T> last = findLast(treeEl);
            Node<T> parent = findParent(treeEl.value);
            Node<T> parentLast = null;

            if (last != null) {
                parentLast = findParent(last.value);
            }

            if (parent == null) {

                if (last != null) {
                    parentLast.left = last.right;
                    last.left = treeEl.left;
                    last.right = treeEl.right;
                    root = last;
                }
                else {
                    treeEl.right.left = root.left;
                    root = treeEl.right;
                }

                size --;
                return true;
            }

            if (parent.left.value.compareTo(treeEl.value) == 0) {
                if (last != null) {
                    parentLast.left = last.right;
                    last.left = treeEl.left;
                    last.right = treeEl.right;
                    parent.left = last;
                }
                else {
                    parent.left = treeEl.right;
                    treeEl.right.left = treeEl.left;
                }
            }
            if (parent.right.value.compareTo(treeEl.value) == 0) {

                if (last != null) {
                    parentLast.left = last.right;
                    last.left = treeEl.left;
                    last.right = treeEl.right;
                    parent.right = last;
                }
                else {
                    parent.right = treeEl.right;
                    treeEl.right.left = treeEl.left;
                }
            }
        }
        size --;
        return true;
    }

    private void del(Node<T> node, Node<T> newNode) {
        Node<T> parent = findParent(node.value);

        if (parent == null && newNode == null) {
            root = null;
            return;
        }
        else if (parent == null && newNode != null){
            root = newNode;
            return;
        }

        if (parent.left == node) parent.left = newNode;
        if (parent.right == node) parent.right = newNode;
    }

    private Node<T> findParent(T value) {
        if (root == null || root.value == value) return null;
        return findParent(value, root);
    }

    private Node<T> findParent(T value, Node<T> node) {
        if ((node.right != null && node.right.value == value) || (node.left != null && node.left.value == value))
            return node;
        int comparison = value.compareTo(node.value);
        if (comparison == 0) {
            return node;
        } else if (comparison < 0) {
            if (node.left == null) return node;
            return findParent(value, node.left);
        } else {
            if (node.right == null) return node;
            return findParent(value, node.right);
        }
    }

    private Node<T> findLast (Node<T> node){
        Node<T> last = null;

        if (node.right.left != null){
            last = node.right;
            while(last.left != null) last = last.left;
        }

        return last;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {}

        private Node<T> findNext() {
            if (current.right != null){
                return findMin(current);
            }
            else if (current.value.compareTo(findParentForNext(current).value) < 0) {
                return findParentForNext(current);
            }
            return null;
        }

        private Node<T> findMin(Node<T> current){
            current = current.right;
            while(current.left != null) current = current.left;
            return current;
        }

        private Node<T> findParentForNext(Node<T> son){
            Node<T> current = root;

            if (current.value.compareTo(son.value) > 0 && current.left.value != son.value) {
                current = current.left;
            }
            else if (current.value.compareTo(son.value) < 0 && current.right.value != son.value) {
                current = current.right;
            }

            return current;
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
