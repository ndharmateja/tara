package data_structures.arrays_linked_lists;

// TODO: add Iterable
public interface List<T> {
    public void addLast(T value);

    public void addFirst(T value);

    public void add(int index, T value);

    public T removeFirst();

    public T removeLast();

    public T remove(int index);

    public T get(int index);

    public void set(int index, T value);

    public int indexOf(T value);

    public boolean contains(T value);

    public int size();

    public boolean isEmpty();

    public void clear();
}
