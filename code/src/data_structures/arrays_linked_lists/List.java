package data_structures.arrays_linked_lists;

// TODO: add Iterable
/**
 * A List is an ADT that stores a sequence elements in a linear fashion.
 * It is a generic interface which can be used to store any type of data.
 */
public interface List<T> {
    /**
     * Adds a new element at the end of the list.
     * 
     * @param value The element to be added.
     */
    public void addLast(T value);

    /**
     * Adds a new element at the start of the list.
     * 
     * @param value The element to be added.
     */
    public void addFirst(T value);

    /**
     * Adds a new element at the specified index in the list.
     * 
     * @param index The index at which the element is to be added.
     * @param value The element to be added.
     */
    public void add(int index, T value);

    /**
     * Removes and returns the element at the start of the list.
     * 
     * @return The element at the start of the list.
     */
    public T removeFirst();

    /**
     * Removes and returns the element at the end of the list.
     * 
     * @return The element at the end of the list.
     */
    public T removeLast();

    /**
     * Removes and returns the element at the specified index in the list.
     * 
     * @param index The index at which the element is to be removed.
     * @return The element at the specified index in the list.
     */
    public T remove(int index);

    /**
     * Returns the element at the specified index in the list.
     * 
     * @param index The index at which the element is to be retrieved.
     * @return The element at the specified index in the list.
     */
    public T get(int index);

    public T getFirst();

    public T getLast();

    /**
     * Sets the element at the specified index in the list.
     * 
     * @param index The index at which the element is to be set.
     * @param value The element to be set.
     */
    public void set(int index, T value);

    /**
     * Returns the index of the first occurrence of the specified element in the
     * list,
     * or -1 if the list does not contain the element.
     * 
     * @param value The element to search for.
     * @return The index of the first occurrence of the specified element in the
     *         list,
     *         or -1 if the list does not contain the element.
     */
    public int indexOf(T value);

    /**
     * Returns true if the list contains the specified element.
     * 
     * @param value The element to search for.
     * @return true if the list contains the specified element.
     */
    public boolean contains(T value);

    /**
     * Returns the number of elements in the list.
     * 
     * @return The number of elements in the list.
     */
    public int size();

    /**
     * Returns true if the list does not contain any elements.
     * 
     * @return true if the list does not contain any elements.
     */
    public boolean isEmpty();

    /**
     * Removes all elements from the list.
     */
    public void clear();
}
