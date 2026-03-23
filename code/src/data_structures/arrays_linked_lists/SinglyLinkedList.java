package data_structures.arrays_linked_lists;

public class SinglyLinkedList<T> implements List<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

    }

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    @Override
    public void addLast(T value) {
        add(size, value);
    }

    @Override
    public void addFirst(T value) {
        add(0, value);
    }

    @Override
    //
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid index");
        }

        // Handle insertion in an empty list
        if (size == 0) {
            // we can assume that index is 0 at this point
            head = tail = new Node<>(value);
            // tail = head;
            size += 1;
            return;
        }

        // Handle insertion at the end
        if (index == size) {
            tail.next = new Node<>(value);
            tail = tail.next;
            size += 1;
            return;
        }

        // At this point, we know that head is not null as list is not empty
        // Handle insertion at the beginning
        if (index == 0) {
            head = new Node<>(value, head);
            size += 1;
            return;
        }

        // At this point, index is valid and list is not empty and we are not inserting
        // at the beginning or the end

        // Start curr at head and move curr 'index-1' number of times
        Node<T> curr = head;
        for (int i = 0; i < index - 1; i++)
            curr = curr.next;

        // Insert new node after curr
        curr.next = new Node<>(value, curr.next);
        size += 1;
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
            throw new IllegalArgumentException("Invalid index.");
        T value;
        // Update both head and tail to be null if it's a single element list
        if (size == 1) {
            value = head.data;
            head = tail = null;
            size--;
            return value;
        }

        // At this point, we know that there are at least 2 elements in the list.
        // Handle the case of removing the head
        if (index == 0) {
            value = head.data;
            head = head.next;
            size--;
            return value;
        }

        // Go to the node at index-1 and set its pointer
        // This works even when index = size - 1 as we are stopping at
        // index - 1
        // so prevNode.next won't be null
        Node<T> prevNode = getNode(index - 1);
        value = prevNode.next.data;
        prevNode.next = prevNode.next.next;

        // Update tail if we are removing the last index
        if (index == size - 1)
            tail = prevNode;
        size--;
        return value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Invalid index.");
        return getNode(index).data;
    }

    @Override
    public T getFirst() {
        if (size == 0)
            throw new IllegalStateException("Empty list.");
        return head.data;
    }

    @Override
    public T getLast() {
        if (size == 0)
            throw new IllegalStateException("Empty list.");
        return tail.data;
    }

    @Override
    public void set(int index, T value) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Invalid index.");
        getNode(index).data = value;

    }

    private Node<T> getNode(int index) {
        if (index == size - 1)
            return tail;
        // Do index number of jumps from head to reach the node with the
        // given index
        Node<T> curr = head;
        for (int i = 0; i < index; i++)
            curr = curr.next;
        return curr;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        for (Node<T> curr = head; curr != null; curr = curr.next) {
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
        size = 0;
        head = tail = null;
    }

    @Override
    // 12 -> 15 -> -1 -> 3 ->
    // 12 ->
    // []
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();

        for (Node<T> curr = head; curr != null; curr = curr.next)
            result.append(curr.data.toString()).append(" -> ");

        // remove the extra space at the end
        result.setLength(result.length() - 1);
        return result.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> intList = new SinglyLinkedList<>();
        System.out.println(intList);
        for (int i = 0; i < 5; i++) {
            intList.addLast(i);
            System.out.println(intList);
        }
    }

}
