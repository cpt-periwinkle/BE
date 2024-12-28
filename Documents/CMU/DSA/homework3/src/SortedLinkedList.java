/**
 * 17683 Data Structures for Application Programmers.
 * Homework 3
 * Sorted Link List
 *
 * SortedLinkedList is a linked list that maintains its elements in sorted order.
 * It supports adding elements, removing elements, and displaying the list, all of it done recursively.
 * It implements the MyListInterface for the functions.
 *
 * Andrew ID: skalekar
 * @author Shlok Kalekar
 */
public class SortedLinkedList implements MyListInterface {

    /**
     * Anonymous inner class to initialize the node object required to create a Linked List.
     * Each node contains a string data and a reference to the next node.
     */
    private static class Node {
        /** String data of the node. */
        private String data;
        /** Reference to the next node in the linked list. */
        private Node next;

        /**
         * Constructor to create a new node with data and the reference to next node.
         *
         * @param d The data for the node
         * @param n The next node in the list
         */
        Node(String d, Node n) {
            data = d;
            next = n;
        }
    }

    /** Node to save start of linked list. */
    private Node head;

    /**
     * Default constructor that initializes an empty linked list.
     */
    public SortedLinkedList() {
        this.head = null;
    }

    /**
     * Constructor that initializes the list with an array of strings, adding them in sorted order.
     *
     * @param stringArray The array of strings to be added to the list
     */
    public SortedLinkedList(String[] stringArray) {
        this();
        addArrayRecursive(stringArray, 0);
    }

    /**
     * Helper method that recursively adds elements from an array to the list.
     *
     * @param array The array to add elements from
     * @param curIndex The current index in the array
     */
    private void addArrayRecursive(String[] array, int curIndex) {
        if (curIndex < array.length) {
            add(array[curIndex]);
            addArrayRecursive(array, curIndex + 1);
        }
    }

    /**
     * Adds a value to the list in sorted order after checking if value received is null.
     *
     * @param value The value to add to the list
     */
    public void add(String value) {
        if ((value != null) && !value.isEmpty() && value.matches("[a-zA-Z]+")) {
            head = addRecursive(value, head);
        }
    }

    /**
     * Helper method that recursively finds the correct place to insert the value in the list.
     *
     * @param value The value to add to the list
     * @param cur The current node being examined
     * @return The new node or the current node if the value is already in the list
     */
    private Node addRecursive(String value, Node cur) {
        if (cur == null) {
            return new Node(value, null);
        }

        int compareFlag = cur.data.compareToIgnoreCase(value);
        if (compareFlag == 0) {
            return cur;
        } else if (compareFlag > 0) {
            return new Node(value, cur);
        } else {
            cur.next = addRecursive(value, cur.next);
            return cur;
        }
    }

    /**
     * Returns the size of the list.
     *
     * @return The number of elements in the list
     */
    public int size() {
        return sizeRecursive(head, 0);
    }

    /**
     * Helper method that recursively calculates the size of the list.
     *
     * @param cur The current node being examined
     * @param count The current count of nodes visited
     * @return The total number of nodes in the list
     */
    private int sizeRecursive(Node cur, int count) {
        if (cur == null) {
            return count;
        }
        return sizeRecursive(cur.next, count + 1);
    }

    /**
     * Displays the list elements in a comma-separated format using StringBuilder.
     * Output will be in the format [element1, element2, ...]
     */
    public void display() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        displayRecursive(head, sb);
        sb.append("]");
        System.out.println(sb.toString());
    }

    /**
     * Helper method that recursively appends each node's data to the StringBuilder.
     *
     * @param cur The current node being examined
     * @param sb The StringBuilder holding the list string representation
     */
    private void displayRecursive(Node cur, StringBuilder sb) {
        if (cur != null) {
            if (sb.length() > 1) { // Avoid adding comma at the start
                sb.append(", ");
            }
            sb.append(cur.data);
            displayRecursive(cur.next, sb);
        }
    }

    /**
     * Checks if the list contains the given value.
     *
     * @param key The value to search for
     * @return True if the value is found, false otherwise
     */
    public boolean contains(String key) {
        return containsRecursive(key, head);
    }

    /**
     * Helper method that recursively searches for the value in the list.
     *
     * @param key The value to search for
     * @param cur The current node being examined
     * @return True if the value is found, false otherwise
     */
    private boolean containsRecursive(String key, Node cur) {
        if (cur == null) {
            return false;
        }

        int compareFlag = cur.data.compareToIgnoreCase(key);
        if (compareFlag == 0) {
            return true;
        } else if (compareFlag > 0) {
            return false;
        } else {
            return containsRecursive(key, cur.next);
        }
    }

    /**
     * Checks if the list is empty.
     *
     * @return True if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Removes the first element from the list and returns its value.
     *
     * @return The value of the first element, or null if the list is empty
     */
    public String removeFirst() {
        if (isEmpty()) {
            return null;
        }
        String popString = head.data;
        head = head.next;
        return popString;
    }

    /**
     * Removes the element at the specified index and returns its value.
     * Throws RuntimeException if invalid index is received.
     *
     * @param index The index of the element to remove
     * @return The value of the removed element, or null if the index is invalid
     */
    public String removeAt(int index) {
        if (index < 0) {
            throw new RuntimeException("Invalid index: " + index);
        }

        if (index == 0) {
            return removeFirst();
        }

        return removeRecursive(index, 1, head.next);
    }

    /**
     * Helper method that recursively removes the element at the specified index.
     *
     * @param searchIndex The index of the element to remove
     * @param curIndex The current index in the list
     * @param cur The current node being examined
     * @return The value of the removed element, or null if the index is invalid
     */
    private String removeRecursive(int searchIndex, int curIndex, Node cur) {
        if (cur == null) {
            return null;
        }
        if (searchIndex == curIndex) {
            String popString = cur.data;
            if (cur.next != null) {
                cur.data = cur.next.data;
                cur.next = cur.next.next;
            } else {
                cur.data = null;
            }
            return popString;
        }
        return removeRecursive(searchIndex, curIndex + 1, cur.next);
    }
}
