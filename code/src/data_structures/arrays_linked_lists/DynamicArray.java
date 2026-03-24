package data_structures.arrays_linked_lists;

import java.util.Arrays;

public class DynamicArray<T> implements List<T> {
    private int size;
    private T[] array;
    private static final int RESIZE_FACTOR = 2;
    private static final int INITIAL_CAPACITY = 4;
    private static final double DOWNSIZING_THRESHOLD = 1.0 / 3;

    public DynamicArray() {
        array = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void increaseCapacity() {
        array = Arrays.copyOf(array, array.length * RESIZE_FACTOR);
    }

    private void decreaseCapacity() {
        if (array.length == INITIAL_CAPACITY)
            return;
        array = Arrays.copyOf(array, array.length / RESIZE_FACTOR);
    }

    @Override
    public void addLast(T value) {
        add(size, value);
    }

    @Override
    public void addFirst(T value) {
        add(0, value);
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid index.");
        if (size == array.length)
            increaseCapacity();
        // Move elements [index, size-1] one step to the right going from right to left.
        for (int i = size - 1; i >= index; i--)
            array[i + 1] = array[i];
        array[index] = value;
        size++;
    }

    @Override
    public T removeFirst() {
        if (size == 0)
            throw new IllegalStateException("Empty list.");
        return remove(0);
    }

    @Override
    public T removeLast() {
        if (size == 0)
            throw new IllegalStateException("Empty list.");
        return remove(size - 1);
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index.");
        if (size <= array.length * DOWNSIZING_THRESHOLD)
            decreaseCapacity();
        T value = array[index];
        // Move [index+1, size-1] elements one step to the left, going from left to
        // right.
        for (int i = index + 1; i <= size - 1; i++)
            array[i - 1] = array[i];
        size--;
        return value;

    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public T getFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFirst'");
    }

    @Override
    public T getLast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLast'");
    }

    @Override
    public void set(int index, T value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public int indexOf(T value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public boolean contains(T value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
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
        array = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    // {5, -11}
    // {}
    public String toString() {
        if (size == 0)
            return "{}";
        StringBuilder res = new StringBuilder("{");
        for (int i = 0; i < size - 1; i++)
            res.append(array[i]).append(", ");
        res.append(array[size - 1]).append("}");
        return res.toString();

    }

    public static void main(String[] args) {
        DynamicArray<Integer> a = new DynamicArray<Integer>();
        System.out.println(a); // {}

        a.addLast(5);
        System.out.println(a);

        a.addLast(-11);

        // {5, -11}
        System.out.println(a);
    }

}
