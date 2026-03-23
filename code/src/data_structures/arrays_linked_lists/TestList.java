package data_structures.arrays_linked_lists;

public class TestList {

    static int totalTests = 0;
    static int passedTests = 0;

    public static void main(String[] args) {
        List<Integer> list = new DoublyLinkedList<>(); // change to your implementation

        testAddLast(list);
        testAddFirst(list);
        testAddIndex(list);
        testGet(list);
        testSet(list);
        testRemove(list);
        testSizeAndIsEmpty(list);

        System.out.println("\n==========================");
        System.out.println("Passed " + passedTests + " out of " + totalTests + " tests.");
        System.out.println("==========================");
    }

    // Utility methods
    static void check(boolean condition, String message) {
        totalTests++;
        if (condition) {
            passedTests++;
        } else {
            System.out.println("❌ FAILED: " + message);
        }
    }

    static void reset(List<Integer> list) {
        while (!list.isEmpty()) {
            list.removeLast();
        }
    }

    // Tests

    static void testAddLast(List<Integer> list) {
        reset(list);

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        check(list.size() == 3, "addLast: size should be 3");
        check(list.get(0) == 1, "addLast: index 0 should be 1");
        check(list.get(2) == 3, "addLast: index 2 should be 3");
    }

    static void testAddFirst(List<Integer> list) {
        reset(list);

        list.addFirst(1);
        list.addFirst(2);

        check(list.size() == 2, "addFirst: size should be 2");
        check(list.get(0) == 2, "addFirst: first element should be 2");
        check(list.get(1) == 1, "addFirst: second element should be 1");
    }

    static void testAddIndex(List<Integer> list) {
        reset(list);

        list.addLast(1);
        list.addLast(3);
        list.add(1, 2);

        check(list.get(1) == 2, "add(index): element at index 1 should be 2");
        check(list.size() == 3, "add(index): size should be 3");
    }

    static void testGet(List<Integer> list) {
        reset(list);

        list.addLast(10);
        list.addLast(20);

        check(list.get(0) == 10, "get: index 0 should be 10");
        check(list.get(1) == 20, "get: index 1 should be 20");
    }

    static void testSet(List<Integer> list) {
        reset(list);

        list.addLast(5);
        list.set(0, 100);

        check(list.get(0) == 100, "set: value should be updated to 100");
    }

    static void testRemove(List<Integer> list) {
        reset(list);

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        int removed = list.remove(1);

        check(removed == 2, "remove(index): removed value should be 2");
        check(list.size() == 2, "remove(index): size should be 2");
        check(list.get(1) == 3, "remove(index): element at index 1 should be 3");
    }

    static void testSizeAndIsEmpty(List<Integer> list) {
        reset(list);

        check(list.isEmpty(), "isEmpty: should be true");

        list.addLast(1);

        check(!list.isEmpty(), "isEmpty: should be false");
        check(list.size() == 1, "size: should be 1");
    }
}
