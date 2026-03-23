package data_structures.arrays_linked_lists;

public class DoublyLinkedList<T> implements List<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> headSentinel = new Node<T>(null);
    private Node<T> tailSentinel = new Node<T>(null);
    private int size = 0;

    public DoublyLinkedList() {
        headSentinel.next = tailSentinel;
        tailSentinel.prev = headSentinel;
    }

    // Assumes that node.next is not null
    private void insertAfter(Node<T> node, Node<T> toInsert) {
        toInsert.next = node.next;
        toInsert.prev = node;
        toInsert.next.prev = toInsert;
        toInsert.prev.next = toInsert;
    }

    // Assumes that node.prev is not null
    private void insertBefore(Node<T> node, Node<T> toInsert) {
        toInsert.prev = node.prev;
        toInsert.next = node;
        toInsert.next.prev = toInsert;
        toInsert.prev.next = toInsert;
    }

    // Assumes node.next and node.prev are not null
    private void remove(Node<T> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private Node<T> getNode(int index) {
        if (index == size - 1)
            return tailSentinel.prev;
        // Do index number of jumps from head.next to reach the node with the
        // given index
        Node<T> curr = headSentinel.next;
        for (int i = 0; i < index; i++)
            curr = curr.next;
        return curr;
    }

    @Override
    public void addLast(T value) {
        insertBefore(tailSentinel, new Node<T>(value));
        size++;
    }

    @Override
    public void addFirst(T value) {
        insertAfter(headSentinel, new Node<T>(value));
        size++;
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid index.");
        insertBefore(getNode(index), new Node<T>(value));
        size++;
    }

    @Override
    public T removeFirst() {
        if (size == 0)
            throw new IllegalStateException("Empty list.");
        T value = headSentinel.next.data;
        remove(headSentinel.next);
        size--;
        return value;
    }

    @Override
    public T removeLast() {
        if (size == 0)
            throw new IllegalStateException("Empty list.");
        T value = tailSentinel.prev.data;
        remove(tailSentinel.prev);
        size--;
        return value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index.");
        Node<T> nodeToRemove = getNode(index);
        T value = nodeToRemove.data;
        remove(nodeToRemove);
        size--;
        return value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index.");
        return getNode(index).data;
    }

    @Override
    public T getFirst() {
        if (size == 0)
            throw new IllegalStateException("Empty list.");
        return headSentinel.next.data;
    }

    @Override
    public T getLast() {
        if (size == 0)
            throw new IllegalStateException("Empty list.");
        return tailSentinel.prev.data;
    }

    @Override
    public void set(int index, T value) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Invalid index.");
        getNode(index).data = value;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        for (Node<T> curr = headSentinel.next; curr != tailSentinel; curr = curr.next) {
            if (curr.data.equals(value)) {
                return index;
            }
            index++;
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
        headSentinel.next = tailSentinel;
        tailSentinel.prev = headSentinel;
        size = 0;
    }

}
