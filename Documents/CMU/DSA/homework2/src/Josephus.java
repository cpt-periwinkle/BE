import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * 17683 Data Structures for Application Programmers.
 * Homework Assignment 2 Solve Josephus problem using different data structures
 * and different algorithms and compare running times.
 *
 * Andrew ID: skalekar
 * @author Shlok Kalekar
 *
 * Which algorithm would I use?
 * For large number of people, the Linked List Method which is calculating using index is
 * the best performing. The reason for this is the fact that there is no shifting operations in this algorithm,
 * meaning that there is only access of the index taking O(n), and its removal at O(1) since it is a linked list,
 * meaning the element references are changed only once per loop.
 * Compared this to ArrayDeque and LinkedList with circular operations, which has multiple shifting operations,
 * while still in O(n), increases running time per operation due to constant change in references, to behave as a
 * circular queue along with removal operation of O(1), then it is understandable that it takes more time due to
 * increased number of operations.
 *
 * Therefore, to save my life, I would use the third algorithm which runs in the least for large numbers
 */
public class Josephus {
    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        // TODO your implementation here
        sizeCheck(size, rotation);
        ArrayDeque<Integer> queue = populateQueue(size);

        while (size > 1)   {
            for (int i = 0; i < rotation - 1; i++)  {
                queue.addLast(queue.removeFirst());
            }
            queue.removeFirst();
            size--;
        }

        return queue.getFirst();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        // TODO your implementation here
        sizeCheck(size, rotation);
        LinkedList<Integer> list = populateLL(size);

        while (size > 1) {
            for (int i = 0; i < rotation - 1; i++) {
                list.addLast(list.removeFirst());
            }
            list.removeFirst();
            size--;
        }

        return list.getFirst();
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     *
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        // TODO your implementation here
        sizeCheck(size, rotation);
        LinkedList<Integer> list = populateLL(size);

        int index = 0;
        while (size > 1)    {
            index = (index + (rotation - 1)) % size;
            list.remove(index);
            size--;
        }

        return list.getFirst();
    }

    /**
     * Populates an ArrayDeque with integers from 1 to given size.
     *
     * @param size The size of the ArrayDeque which needs elements added to.
     * @return An ArrayDeque containing integers from 1 to size.
     */
    private ArrayDeque<Integer> populateQueue(int size) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= size; i++) {
            queue.add(i);
        }
        return queue;
    }

    /**
     * Populates a LinkedList with integers from 1 to given size.
     *
     * @param size The size of the LinkedList which needs elements added to.
     * @return A LinkedList containing integers from 1 to size.
     */
    private LinkedList<Integer> populateLL(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * Validates the size and rotation parameters to ensure they are greater than 0.
     * If either size or rotation is less than 1, a RuntimeException is thrown.
     *
     * @param size The number of elements to be added to the Collection Structure.
     * @param rotation The elimination order for the elements.
     * @throws RuntimeException if either size or rotation is less than 1.
     */
    private void sizeCheck(int size, int rotation)    {
        if (size < 1 || rotation < 1)   {
            throw new RuntimeException("Size and rotation must be greater than 0!");
        }
    }
}
