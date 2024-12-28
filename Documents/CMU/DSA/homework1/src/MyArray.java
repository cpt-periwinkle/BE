/**
 * MyArray class that implements resizable String arrays.
 * Contains additional functionalities such as searching, duplication removal and array display.
 * @author skalekar, Shlok Kalekar.
 */
public class MyArray {
    /** Array to hold string elements. */
    private String[] myArray;

    /** Number of elements currently in the array. */
    private int fillValue = 0;

    /** Initial constant when array has more than 0 elements. */
    private static final int FIRST_CAPACITY = 1;


    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Default constructor that initializes the array with a capacity of 10 (default capacity).
     * Time complexity: O(1).
     */
    public MyArray() {
        myArray = new String[DEFAULT_CAPACITY];
    }

    /**
     * Constructor that initializes the array with given initial capacity.
     * @param initialCapacity the initial size of the array.
     * Time complexity: O(1).
     */
    public MyArray(int initialCapacity) {
        myArray = new String[initialCapacity];
    }

    /**
     * Adds word to the array.
     * If the array is full, it doubles its capacity (as per assignment).
     * Only adds non-null, non-empty, alphabetic words after trimming.
     * @param word the word to add to the array.
     * Amortized time complexity: O(1).
     * During resizing, time complexity: O(n) where n is number of elements in the array.
     */
    public void add(String word) {
        if ((word != null) && !word.isEmpty() && word.matches("[a-zA-Z]+")) {
            this.arrayLengthChecker();
            myArray[fillValue++] = word;
        }
    }

    /**
     * Searches for given word in the array.
     * Assumes that add method has correctly performed
     * and any null values are unfilled elements in the array.
     * @param searchWord the word to search for in the array.
     * @return true if word is found, otherwise return false.
     * Time complexity: O(n) where n is number of elements in the array.
     */
    public boolean search(String searchWord) {
        boolean searchFlag = false;
        if (searchWord == null) {
            return false;
        }
        for (String element : myArray) {
            if (element == null) {
                break;
            }
            if (searchWord.equalsIgnoreCase(element)) {
                searchFlag = true;
                break;
            }
        }
        return searchFlag;
    }

    /**
     * Returns the current number of elements in the array.
     * @return the number of elements currently in the array.
     * Time complexity: O(1).
     */
    public int size() {
        return fillValue;
    }

    /**
     * Returns the current capacity of the array.
     * @return the total length of the array.
     * Time complexity: O(1).
     */
    public int getCapacity() {
        return myArray.length;
    }

    /**
     * Displays the contents of the array as a single line of words separated by white spaces.
     * Assumes that add method has correctly performed
     * and any null values are unfilled elements in the array.
     * Time complexity: O(n) where n is number of elements in the array.
     */
    public void display() {
        StringBuilder sb = new StringBuilder();
        for (String element : myArray) {
            if (element == null)  {
                break;
            }
            sb.append(element).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    /**
     * Removes duplicates from the array.
     * Shifts elements as required to maintain continuity in the array structure.
     * Sets the last element before the shift to null once shift is done if duplicate found.
     * Time complexity: O(n^2) where n is number of elements in the array.
     */
    public void removeDups() {
        for (int i = 0; i < fillValue; i++) {
            for (int j = i + 1; j < fillValue; j++) {
                if (myArray[i].equals(myArray[j])) {
                    this.insertArray(j);
                    fillValue--;
                    myArray[fillValue] = null;
                    j--;
                }
            }
        }
    }

    /**
     * Checks if array length fits number of elements required.
     * Doubles the size of the array when it is full (as per assignment).
     * Time complexity of array length changer itself: O(1).
     * Time complexity due to System.arraycopy: O(n) where n is number of elements in the array.
     */
    private void arrayLengthChecker() {
        if (fillValue == myArray.length)    {
            int newCapacity = (myArray.length == 0) ? FIRST_CAPACITY : myArray.length * 2;
            String[] newArray = new String[newCapacity];
            System.arraycopy(myArray, 0, newArray, 0, myArray.length);
            myArray = newArray;
        }
    }

    /**
     * Shifts elements to the left to fill the gap after removing an element.
     * @param nextValue the index of the element to be removed and shifted.
     * Time complexity: O(n) where n is number of elements in the array.
     */
    private void insertArray(int nextValue) {
        for (int k = nextValue; k < fillValue - 1; k++) {
            myArray[k] = myArray[k + 1];
        }
    }
}
