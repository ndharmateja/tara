package data_structures.arrays_linked_lists;

public class Array<T> implements List<T> {
    private int size = 0;
    private T[] array;
    private static final int DEFAULT_CAPACITY = 10;

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public Array(int capacity) {
        array = (T[]) new Object[capacity];
    }

    @Override
    public void addLast(T value) {
        // array[size++] = value;
        add(size, value);
    }

    @Override
    public void addFirst(T value) {
        add(0, value);
    }

    @Override
    public void add(int index, T value) {
        // If array is full, we can't add
        if (array.length == size) {
            throw new IllegalStateException("Array is full.");
        }

        // valid indices are from 0 to size
        if ((index < 0) || (index > size)) {
            throw new IllegalArgumentException("Invalid index.");
        }
        // 1. move elements from [index, size-1] by one step to the right (from right to
        // left)
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }

        // 2. assign value to index
        array[index] = value;

        // 3. increment size
        size++;

    }

    @Override
    public T removeFirst() {
        return remove(0);
    }

    @Override
    public T removeLast() {
        return remove(size - 1);
    }

    @Override
    public T remove(int index) {
        // Valid indices are from 0 to size-1
        if ((index < 0) || (index > size - 1)) {
            throw new IllegalArgumentException("Invalid index.");
        }
        T removedElement = array[index];
        // 1. Move elements from index + 1 to size - 1 one step to the left.
        for (int i = index + 1; i <= size - 1; i++) {
            array[i - 1] = array[i];
        }

        // We are not explicitly making the element at index oldSize-1 to be null
        // even though it is not part of the data as we only consider elements
        // in the range [0, size-1] as part of our data

        // 2. Decrement size.
        size--;
        // 3. Return removed element.
        return removedElement;

    }

    @Override
    public T get(int index) {
        // Valid indices are [0, size-1]
        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException("Invalid index");
        return array[index];
    }

    @Override
    public T getFirst() {
        if (size == 0)
            throw new IllegalStateException("Empty list.");
        return array[0];
    }

    @Override
    public T getLast() {
        if (size == 0)
            throw new IllegalStateException("Empty list.");
        return array[size - 1];
    }

    @Override
    public void set(int index, T value) {
        // Valid indices are [0, size-1]
        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException("Invalid index");
        array[index] = value;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i <= size - 1; i++) {
            if (value.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }

        // StringBuilder
        StringBuilder result = new StringBuilder("{");
        for (int i = 0; i < size - 1; i++) {
            result.append(array[i].toString()).append(", ");
        }
        result.append(array[size - 1].toString()).append("}");
        return result.toString();
    }

    public static void main(String[] args) {
        Array<Integer> a = new Array<Integer>(10);
        System.out.println(a); // {}

        a.addLast(5);
        System.out.println(a);

        a.addLast(-11);

        // {5, -11}
        System.out.println(a);
    }

}
