import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 17683 Data Structures for Applications Programmers.
 * Homework 6
 *
 * This class implements a Binary Search Tree (BST), a data structure where each node contains data,
 * and left and right children, such that for any node, the left child's value is less than the node's value,
 * and the right child's value is greater than the node's value.
 *
 * The BST supports basic operations like insertion, search, and height computation.
 *
 * @param <T> The type of data stored in the BST, which must implement Comparable.
 *
 * Andrew ID: skalekar
 * @author Shlok Kalekar
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /** Variable stores reference to root node. */
    private Node<T> root;
    /** Variable stores reference to current comparator being used. */
    private Comparator<T> comparator;

    /**
     * Constructs a new Binary Search Tree without a custom comparator.
     * The comparator is set to null.
     */
    public BST() {
        this(null);
    }

    /**
     * Constructs a new Binary Search Tree with the specified comparator.
     *
     * @param comp The comparator to be used for comparing elements in the tree.
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    /**
     * Gets the comparator used for the tree.
     *
     * @return The comparator used to compare the elements in the tree.
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     * Gets the root element of the tree.
     *
     * @return The root element of the tree, or null if the tree is empty.
     */
    public T getRoot() {
        return root != null ? root.data : null;
    }

    /**
     * Gets the height of the tree.
     * The height of an empty tree is 0.
     *
     * @return The height of the tree.
     */
    public int getHeight() {
        if (root == null)  {
            return 0;
        }
        return getHeightRecursive(root);
    }

    /**
     * Recursively calculates the height of the tree.
     *
     * Base case: If the current node is null, return -1.
     * Recursion case: The height is 1 plus the maximum height of the left and right subtrees.
     *
     * @param current The current node being evaluated.
     * @return The height of the tree starting from the current node.
     */
    private int getHeightRecursive(Node<T> current) {
        if (current == null) {
            return -1;
        }
        int leftHeight = getHeightRecursive(current.left);
        int rightHeight = getHeightRecursive(current.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Gets the total number of nodes in the tree.
     *
     * @return The number of nodes in the tree.
     */
    public int getNumberOfNodes() {
        return getNumberOfNodesRecursive(root);
    }

    /**
     * Recursively calculates the number of nodes in the tree.
     *
     * Base case: If the current node is null, the node count is 0.
     * Recursion case: The count is 1 (for the current node) plus the node counts of the left and right subtrees.
     *
     * @param current The current node being evaluated.
     * @return The number of nodes starting from the current node.
     */
    private int getNumberOfNodesRecursive(Node<T> current) {
        if (current == null) {
            return 0;
        }
        return 1 + getNumberOfNodesRecursive(current.left) + getNumberOfNodesRecursive(current.right);
    }

    /**
     * Searches for an element in the tree.
     *
     * Base case: If the current node is null or the element is found, return the element.
     * Recursion case: Search the left or right subtree depending on the comparison.
     *
     * @param toSearch The element to search for.
     * @return The element if found, or null if not found.
     */
    @Override
    public T search(T toSearch) {
        return searchRecursive(root, toSearch);
    }

    /**
     * Recursively searches for an element in the tree.
     *
     * Base case: If the current node is null or the element is found, return the element.
     * Recursion case: Depending on the comparison, search either the left or right subtree.
     *
     * @param current The current node being evaluated.
     * @param toSearch The element to search for.
     * @return The element if found, or null if not found.
     */
    private T searchRecursive(Node<T> current, T toSearch)  {
        if (current == null || toSearch == null) {
            return null;
        }

        int comparison = (comparator != null)
                ? comparator.compare(toSearch, current.data) : toSearch.compareTo(current.data);

        if (comparison == 0) {
            return current.data;
        } else if (comparison < 0) {
            return searchRecursive(current.left, toSearch);
        } else {
            return searchRecursive(current.right, toSearch);
        }
    }

    /**
     * Inserts an element into the tree.
     *
     * Base case: If the tree is empty, create a new node.
     * Recursion case: Depending on the comparison, insert into the left or right subtree.
     *
     * @param toInsert The element to insert.
     */
    @Override
    public void insert(T toInsert) {
        if (toInsert == null)   {
            return;
        }
        root = insertRecursive(root, toInsert);
    }

    /**
     * Recursively inserts an element into the tree.
     *
     * Base case: If the current node is null, create a new node with the element.
     * Recursion case: Depending on the comparison, insert into the left or right subtree.
     *
     * @param current The current node being evaluated.
     * @param toInsert The element to insert.
     * @return The new node or updated node after insertion.
     */
    private Node<T> insertRecursive(Node<T> current, T toInsert) {
        if (current == null) {
            return new Node<>(toInsert);
        }

        int comparison = (comparator != null)
                ? comparator.compare(toInsert, current.data)
                : toInsert.compareTo(current.data);

        if (comparison < 0) {
            current.left = insertRecursive(current.left, toInsert);
        } else if (comparison > 0) {
            current.right = insertRecursive(current.right, toInsert);
        }

        return current;
    }

    /**
     * Creates an iterator for the tree to traverse the elements in in-order.
     *
     * @return An iterator that allows in-order traversal of the tree.
     */
    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<T> {
        /** Declares Stack data structure for traversal. */
        private Stack<Node<T>> treeStack;

        /**
         * Constructs an iterator starting from the root of the tree.
         */
        InOrderIterator() {
            treeStack = new Stack<>();
            pushLeftChildren(root);
        }

        /**
         * Push all left children of the given node onto the stack.
         * @param current The node being pushed into the stack for traversal.
         */
        private void pushLeftChildren(Node<T> current) {
            while (current != null) {
                treeStack.push(current);
                current = current.left;
            }
        }

        /**
         * Checks if the stack is empty to check if more to iterate through.
         *
         * @return True if there are more elements, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return !treeStack.isEmpty();
        }

        /**
         * Returns the next element in the in-order traversal.
         *
         * @return The next element in the traversal.
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to traverse.");
            }

            Node<T> next = treeStack.pop();
            if (next.right != null) {
                pushLeftChildren(next.right);
            }
            return next.data;
        }
    }

    /**
     * Represents a node in the binary search tree.
     *
     * @param <T> The type of data stored in the node.
     */
    private static class Node<T> {
        /** Generic data being stored in Node. */
        private T data;
        /** Reference to left child of Node. */
        private Node<T> left;
        /** Reference to right child of Node. */
        private Node<T> right;

        Node(T d) {
            this(d, null, null);
        }

        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }
    }

}
