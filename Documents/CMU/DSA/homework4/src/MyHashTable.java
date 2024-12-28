/**
 * 17683 Data Structures for Application Programmers.
 * Homework Assignment 4: HashTable Implementation with linear probing.
 *
 * Andrew ID: skalekar
 * @author Shlok Kalekar
 */
public class MyHashTable implements MyHTInterface {
    /** Default array length for hashArray. */
    private static final int DEFAULT_CAPACITY = 10;
    /** Default load factor for MyHashTable. */
    private static final double DEFAULT_LOAD_FACTOR = 0.5;
    /** Deleted DataItem dummy constant. */
    private static final DataItem DELETED = new DataItem("#DEL#");
    /** Given hash initializer. */
    private static final int DEFAULT_HASH_INITIALIZER = 27;
    /** Value of ASCII 'a' - 1. */
    private static final int LOWERCASE_ASCII_BOUND = 96;
    /** The smallest non-zero integer value. */
    private static final int FIRST_INT = 1;
    /** The first prime number. */
    private static final int FIRST_PRIME = 2;
    /** The first odd number to check after 2. */
    private static final int FIRST_ODD = 3;
    /** Step size to check for primes (since all other than FIRST_PRIME are odd). */
    private static final int STEP_SIZE = 2;

    /** Count for size of hashMap. */
    private int fillValue;
    /** Count for number of collisions during hashing. */
    private int collisionsCount;
    /**
     * The DataItem array of the table.
     */
    private DataItem[] hashArray;

    /**
     * Default constructor for the MyHashTable class.
     * Initializes the hash table with the default capacity.
     */
    public MyHashTable()  {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor for the MyHashTable class with an initial capacity.
     * Initializes the hash table with the specified capacity.
     * Initializes fillValue(size of hash table) and collisionsCount to 0.
     *
     * @param capacity the initial capacity of the hash table.
     *                 Must be a positive integer.
     * @throws RuntimeException if the specified capacity is less than or equal to zero.
     */
    public MyHashTable(int capacity)  {
        if (capacity <= 0)  {
            throw new RuntimeException("Invalid Capacity");
        }
        hashArray = new DataItem[capacity];
        fillValue = 0;
        collisionsCount = 0;
    }

    /**
     * Inserts a value into the hash table.
     * Validates the value to ensure it contains only lowercase alphabetical characters.
     * Automatically rehashes the table if the load factor exceeds the default threshold.
     *
     * @param value the value to be inserted. Must be a non-null, non-empty string containing only lowercase letters.
     */
    public void insert(String value) {
        if ((value != null) && !value.isEmpty() && value.matches("[a-z]+")) {
            insert(value, 0);
            if ((double) fillValue / hashArray.length > DEFAULT_LOAD_FACTOR) {
                rehash();
            }
        }
    }

    /**
     * Helper method to insert a value into the hash table with a specified initial frequency.
     * Initial frequency condition created for use in adding old frequency during rehashing.
     * Handles collisions using linear probing and maintains a count of collisions encountered
     * as per separate chaining definition of collision.
     * If the value already exists, its frequency is updated.
     *
     * @param value the value to be inserted. Must be a non-null, non-empty string.
     * @param oldFrequency the initial frequency of the value (useful for re-insertions during rehashing).
     */
    private void insert(String value, int oldFrequency)    {
        boolean collisionFlag = false;
        int hashVal = hashFunc(value);
        // Original hashVal stored for collision checking.
        int originalVal = hashVal;
        // Location of first encountered delete variable initialized to non-index value of -1.
        int firstDelete = -1;
        while (hashArray[hashVal] != null) {
            // Checks if newly inserted variable (has no older frequency) exists.
            if (hashArray[hashVal].value.equals(value) && oldFrequency == 0) {
                hashArray[hashVal].frequency++;
                return;
            }
            // Checks if value being iterated over is deleted, stores it if its first deleted variable in chain.
            if (hashArray[hashVal] == DELETED && firstDelete == -1)  {
                firstDelete = hashVal;
            }
            // Checks if there is a collision as per separate chaining rules of collision (have same hashVal).
            if (originalVal == hashFunc(hashArray[hashVal].value)) {
                collisionFlag = true;
            }
            hashVal = (hashVal + 1) % hashArray.length;
            // Checks if hashTable has been completely looped over once to stop infinite looping.
            if (originalVal == hashVal) {
                break;
            }
        }
        // Checks if collision has occurred. Collisions only count once per chain.
        if (collisionFlag)  {
            collisionsCount++;
        }
        // Inserts location of first deleted DataItem encountered if present.
        if (firstDelete != -1)  {
            hashVal = firstDelete;
        }
        hashArray[hashVal] = new DataItem(value);
        // Inserts old frequency value (used in rehash).
        if (oldFrequency != 0) {
            hashArray[hashVal].frequency = oldFrequency;
        }
        fillValue++;
    }

    /**
     * Returns the number of elements currently stored in the hash table.
     *
     * @return the number of elements in the hash table.
     */
    public int size()   {
        return fillValue;
    }

    /**
     * Displays the current state of the hash table.
     *
     * Each slot in the hash table is represented as follows:
     * - `**` for empty slots.
     * - `#DEL#` for deleted slots.
     * - `[value, frequency]` for occupied slots, where `value` is the stored value, and `frequency` is its frequency.
     *
     * The contents are printed in a single line, showing the state of each slot in the hash array sequentially.
     */
    public void display() {
        StringBuilder sb = new StringBuilder();
        for (DataItem dataItem : hashArray) {
            if (dataItem == null) {
                sb.append("** ");
            } else if (dataItem == DELETED) {
                sb.append("#DEL# ");
            } else {
                sb.append("[").append(dataItem.value)
                        .append(", ").append(dataItem.frequency)
                        .append("] ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        System.out.println(sb.toString());
    }

    /**
     * Checks if the specified key exists in the hash table.
     *
     * @param key the key to search for in the hash table.
     *            Must be a non-null, non-empty string containing only lowercase letters.
     * @return {@code true} if the key is found in the hash table; {@code false} otherwise.
     *
     * The method performs the following steps:
     * - Validates the key format.
     * - Uses the hash function to compute the starting position.
     * - Probes through the table using linear probing to search for the key.
     *
     * If the key is marked as deleted or the entire table is traversed without finding the key,
     * the method returns {@code false}.
     */
    public boolean contains(String key) {
        if (key == null || key.isEmpty() || !key.matches("[a-z]+"))    {
            return false;
        }
        int hashVal = hashFunc(key);
        int originalVal = hashVal;
        while (hashArray[hashVal] != null) {
            // Checks if same item exists at the specified hashValue.
            if (hashArray[hashVal] != DELETED && hashArray[hashVal].value.equals(key)) {
                return true;
            }
            hashVal = (hashVal + 1) % hashArray.length;
            // Checks if hashTable has been completely looped over once to stop infinite looping.
            if (hashVal == originalVal) {
                break;
            }
        }
        return false;
    }

    /**
     * Returns the total number of collisions that have occurred in the hash table.
     *
     * A collision occurs when two different keys hash to the same index,
     * requiring the hash table to resolve the conflict through linear probing.
     *
     * @return the number of collisions encountered during insertions.
     */
    public int numOfCollisions()   {
        return collisionsCount;
    }

    /**
     * Computes the hash value for the given key using the hash function.
     *
     * @param value the value for which the hash value is to be computed.
     *            Must be a non-null string.
     * @return the hash value of the key, or {@code -1} if the key is {@code null}.
     *
     * The method delegates the computation to the internal hash function and
     * returns its result, ensuring that a null key is handled gracefully.
     */
    public int hashValue(String value) {
        if (value == null) {
            return -1;
        }
        return hashFunc(value);
    }

    /**
     * Retrieves the frequency of the specified key in the hash table.
     *
     * @param key the key whose frequency is to be retrieved.
     *            Must be a non-null, non-empty string containing only lowercase letters.
     * @return the frequency of the key in the hash table, or {@code 0} if the key is not found
     *         or is invalid.
     *
     * The method performs the following:
     * - Validates the key format.
     * - Uses the hash function to compute the starting position.
     * - Probes the table using linear probing to locate the key.
     *
     * If the key is found, its frequency is returned. If the key does not exist,
     * is marked as deleted, or the table is fully traversed without finding the key,
     * the method returns {@code 0}.
     */
    public int showFrequency(String key)   {
        if (key == null || key.isEmpty() || !key.matches("[a-z]+"))    {
            return 0;
        }
        int hashVal = hashFunc(key);
        int originalVal = hashVal;
        while (hashVal >= 0 && hashArray[hashVal] != null) {
            // Checks if same item exists at the specified hashValue.
            if (hashArray[hashVal] != DELETED && hashArray[hashVal].value.equals(key)) {
                return hashArray[hashVal].frequency;
            }
            hashVal = (hashVal + 1) % hashArray.length;
            // Checks if hashTable has been completely looped over once to stop infinite looping.
            if (hashVal == originalVal) {
                break;
            }
        }
        return 0;
    }

    /**
     * Removes the specified key from the hash table.
     *
     * @param key the key to be removed.
     *            Must be a non-null, non-empty string containing only lowercase letters.
     * @return the value of the removed key, or {@code null} if the key is not found
     *         or is invalid.
     *
     * The method performs the following steps:
     * - Validates the key format.
     * - Uses the hash function to compute the starting position.
     * - Probes the table using linear probing to locate the key.
     * - If the key is found:
     *   - Marks the slot as deleted.
     *   - Decrements the `fillValue` counter.
     *   - Returns the value of the removed key.
     * - If the key is not found or does not exist, the method returns {@code null}.
     */
    public String remove(String key) {
        if (key == null || key.isEmpty() || !key.matches("[a-z]+"))    {
            return null;
        }
        int hashVal = hashFunc(key);
        int originalVal = hashVal;
        while (hashArray[hashVal] != null) {
            // Checks if same item exists at the specified hashValue.
            if (hashArray[hashVal] != DELETED && hashArray[hashVal].value.equals(key)) {
                DataItem item = hashArray[hashVal];
                hashArray[hashVal] = DELETED;
                fillValue--;
                return item.value;
            }
            hashVal = (hashVal + 1) % hashArray.length;
            // Checks if hashTable has been completely looped over once to stop infinite looping.
            if (hashVal == originalVal) {
                break;
            }
        }
        return null;
    }

    /**
     * Computes the hash value for the given input string using a custom hash function.
     * Handles both indexing and compression for hashing.
     *
     * @param input input string for which the hash value needs to be calculated.
     *              Must be a non-null string.
     * @return int hash value of the input string, constrained to the size of the hash table.
     *
     * The hash function:
     * - Iterates through each character of the input string.
     * - Converts the character to an integer value by subtracting {@code LOWERCASE_ASCII_BOUND}.
     * - Updates the hash value using a multiplier ({@code DEFAULT_HASH_INITIALIZER}) and modular arithmetic.
     * - Ensures that the final hash value fits within the bounds of the hash table's size.
     */
    private int hashFunc(String input) {
        int hashVal = 0;
        for (int i = 0; i < input.length(); i++) {
            int charVal = (int) input.charAt(i) - LOWERCASE_ASCII_BOUND;
            hashVal = ((hashVal * DEFAULT_HASH_INITIALIZER) + charVal) % hashArray.length;
        }
        return hashVal;
    }

    /**
     * Resizes the hash table by doubling its size and rehashing all existing items.
     *
     * This method performs the following steps:
     * - Computes a new size for the hash table by finding the next prime number
     *   that is at least twice the current size.
     * - Prints a message indicating the number of items being rehashed and the new size.
     * - Creates a new array to hold the rehashed items.
     * - Resets the collision count and fill value.
     * - Re-inserts all the non-null and non-deleted items from the old hash table into the new table.
     *
     * Rehashing is triggered when the load factor exceeds a threshold,
     * and it helps maintain the performance of the hash table by reducing collisions.
     */
    private void rehash() {
        int newArrayLength = nextPrime(hashArray.length * 2);
        System.out.println("Rehashing " + fillValue + " items, new length is " + newArrayLength);
        DataItem[] tempArray = hashArray;
        hashArray = new DataItem[newArrayLength];
        collisionsCount = 0;
        fillValue = 0;

        // Iterates through old hash table to insert into new hash table with increased length.
        for (DataItem dataItem : tempArray) {
            if (dataItem != null && dataItem != DELETED) {
                insert(dataItem.value, dataItem.frequency);
            }
        }
    }

    /**
     * Finds the next prime number greater than or equal to the given number.
     *
     * This method checks if the given number is prime, and if not, it iterates
     * to find the next prime number. The search starts from the given number
     * and continues with odd numbers, skipping even numbers after checking
     * divisibility by the first prime (2).
     *
     * @param num the number for which the next prime is to be found.
     *            Must be greater than or equal to 2.
     * @return the next prime number greater than or equal to the given number.
     *
     * If the input is a small value like 1 or the first prime, the method
     * handles those cases directly and returns the first prime number (2).
     */
    private int nextPrime(int num) {
        if (num == FIRST_INT || num == FIRST_PRIME) {
            return FIRST_PRIME;
        }

        if (num % FIRST_PRIME == 0) {
            num++;
        }

        while (true) {
            boolean isPrime = true;
            for (int i = FIRST_ODD; i * i <= num; i += STEP_SIZE) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                return num;
            }
            num += STEP_SIZE;
        }
    }


    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private final String value;
        /**
         * String value's frequency.
         */
        private int frequency;

        /**
         * Constructs a new DataItem with the specified value and an initial frequency of 1.
         *
         * @param val the value to be stored in the DataItem.
         *            Must be a non-null string.
         *
         * The constructor initializes the `value` field with the given string and sets
         * the `frequency` field to 1, indicating that this value has been inserted once.
         */
        DataItem(String val)   {
            this.value = val;
            this.frequency = 1;
        }
    }

}
