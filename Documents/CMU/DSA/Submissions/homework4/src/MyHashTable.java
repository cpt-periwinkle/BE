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
    /** Deleted DataItem dummy variable. */
    private static final DataItem DELETED = new DataItem("#DEL#");
    /** Given hash initializer. */
    private static final int DEFAULT_HASH_INITIALIZER = 27;
    /** Value of ASCII a - 1. */
    private static final int LOWERCASE_ASCII_BOUND = 96;
    /** The smallest prime number. */
    private static final int FIRST_PRIME = 2;
    /** The first odd number to check after 2. */
    private static final int FIRST_ODD = 3;
    /** Step size. */
    private static final int STEP_SIZE = 2;


    /** Count for size of hashMap. */
    private int fillValue;
    /** Count for number of collisions during hashing. */
    private int collisionsCount;
    /**
     * The DataItem array of the table.
     */
    private DataItem[] hashArray;

    // TODO implement constructor with no initial capacity
    public MyHashTable()  {
        this(DEFAULT_CAPACITY);
    }

    // TODO implement constructor with initial capacity
    public MyHashTable(int capacity)  {
        if (capacity <= 0)  {
            throw new RuntimeException("Invalid Capacity");
        }
        hashArray = new DataItem[capacity];
        fillValue = 0;
        collisionsCount = 0;
    }

    // TODO implement required methods
    public void insert(String key) {
        if ((key != null) && !key.isEmpty() && key.matches("[a-zA-Z]+")) {
            insert(key, 0);
            if ((double) fillValue / hashArray.length > DEFAULT_LOAD_FACTOR) {
                rehash();
            }
        }
    }

    private void insert(String key, int frequency)    {
        boolean collisionFlag = false;
        int hashVal = hashFunc(key);
        int originalVal = hashVal;
        while (hashArray[hashVal] != null && hashArray[hashVal] != DELETED) {
            if (hashArray[hashVal].value.equals(key) && frequency == 0) {
                hashArray[hashVal].frequency++;
                return;
            }
            if (!collisionFlag && originalVal == hashFunc(hashArray[hashVal].value)) {
                collisionFlag = true;
            }
            hashVal = (hashVal + 1) % hashArray.length;
        }
        if (collisionFlag)  {
            collisionsCount++;
        }
        hashArray[hashVal] = new DataItem(key);
        if (frequency != 0) {
            hashArray[hashVal].frequency = frequency;
        }
        fillValue++;
    }

    public int size()   {
        return fillValue;
    }

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


    public boolean contains(String key) {
        if (key == null || key.isEmpty() || !key.matches("[a-z]+"))    {
            return false;
        }
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal] != DELETED && hashArray[hashVal].value.equals(key)) {
                return true;
            }
            hashVal = (hashVal + 1) % hashArray.length;
        }
        return false;
    }

    public int numOfCollisions()   {
        return collisionsCount;
    }

    public int hashValue(String key) {
        if (key == null) {
            return -1;
        }
        return hashFunc(key);
    }

    public int showFrequency(String key)   {
        if (key == null || key.isEmpty() || !key.matches("[a-z]+"))    {
            return 0;
        }
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal] != DELETED && hashArray[hashVal].value.equals(key)) {
                return hashArray[hashVal].frequency;
            }
            hashVal = (hashVal + 1) % hashArray.length;
        }
        return 0;
    }

    public String remove(String key) {
        if (key == null || key.isEmpty() || !key.matches("[a-z]+"))    {
            return null;
        }
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal] != DELETED && hashArray[hashVal].value.equals(key)) {
                DataItem item = hashArray[hashVal];
                hashArray[hashVal] = DELETED;
                fillValue--;
                return item.value;
            }
            hashVal = (hashVal + 1) % hashArray.length;
        }
        return null;
    }
    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
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
     * doubles array length and rehash items whenever the load factor is reached.
     * Note: do not include the number of deleted spaces to check the load factor.
     * Remember that deleted spaces are available for insertion.
     */
    private void rehash() {
        int newArrayLength = nextPrime(hashArray.length * 2);
        System.out.println("Rehashing " + fillValue + " items, new length is " + newArrayLength);
        DataItem[] tempArray = hashArray;
        hashArray = new DataItem[newArrayLength];
        collisionsCount = 0;
        fillValue = 0;

        for (DataItem dataItem : tempArray) {
            if (dataItem != null && dataItem != DELETED) {
                insert(dataItem.value, dataItem.frequency);
            }
        }
    }

    private int nextPrime(int num) {
        // Handle case for numbers less than or equal to 1
        if (num <= 1) {
            return DEFAULT_CAPACITY; // The smallest prime
        }

        // Handle case where num is 2, the first prime number
        if (num == FIRST_PRIME) {
            return FIRST_PRIME;
        }

        // If num is even (except 2), make it odd by incrementing by 1
        if (num % FIRST_PRIME == 0) {
            num++;
        }

        // Continue until we find a prime
        while (true) {
            boolean isPrime = true;
            // We check divisibility up to the square root of num
            for (int i = FIRST_ODD; i * i <= num; i += STEP_SIZE) { // i starts at FIRST_ODD and skips even numbers
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                return num;
            }
            num += STEP_SIZE; // Only check odd numbers
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

        // TODO implement constructor and methods
        DataItem(String val)   {
            this.value = val;
            this.frequency = 1;
        }
    }

}
