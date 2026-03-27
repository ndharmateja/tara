package data_structures.arrays_linked_lists;

public class TestList {

    static int totalTests = 0;
    static int passedTests = 0;

    public static void main(String[] args) {
        List<Integer> list = new DynamicArray<>();

        testAddLast(list);
        testAddFirst(list);
        testAddIndex(list);
        testGet(list);
        testGetFirstAndLast(list);
        testSet(list);
        testRemove(list);
        testRemoveFirstAndLast(list);
        testIndexOfAndContains(list);
        testSizeAndIsEmpty(list);
        testClear(list);
        testStress(list);

        System.out.println("\n========================================");
        if (totalTests == passedTests) {
            System.out.println("✅ ALL CLEAR! Passed " + passedTests + " out of " + totalTests + " tests.");
        } else {
            System.out.println("❌ WARNING! Passed " + passedTests + " out of " + totalTests + " tests.");
        }
        System.out.println("========================================");
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

    // Using your interface's clear() method now, but falling back to removeLast
    // just in case clear isn't implemented yet
    static void reset(List<Integer> list) {
        try {
            list.clear();
        } catch (Exception e) {
            while (!list.isEmpty()) {
                list.removeLast();
            }
        }
    }

    // --- Core Tests ---

    static void testAddLast(List<Integer> list) {
        reset(list);

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        check(list.size() == 3, "addLast: size should be 3");
        check(list.get(0) == 1, "addLast: index 0 should be 1");
        check(list.get(2) == 3, "addLast: index 2 should be 3");

        reset(list);
        list.addLast(100);
        check(list.size() == 1, "addLast: size should be 1 after reset and add");
        check(list.get(0) == 100, "addLast: index 0 should be 100 after reset");
    }

    static void testAddFirst(List<Integer> list) {
        reset(list);

        list.addFirst(1);
        check(list.size() == 1, "addFirst: size should be 1 when adding to empty");
        check(list.get(0) == 1, "addFirst: first element should be 1");

        list.addFirst(2);
        list.addFirst(3);

        check(list.size() == 3, "addFirst: size should be 3");
        check(list.get(0) == 3, "addFirst: index 0 should be 3");
        check(list.get(1) == 2, "addFirst: index 1 should be 2");
        check(list.get(2) == 1, "addFirst: index 2 should be 1");
    }

    static void testAddIndex(List<Integer> list) {
        reset(list);

        list.add(0, 10);
        check(list.get(0) == 10, "add(index): element at 0 should be 10 on empty list");
        check(list.size() == 1, "add(index): size should be 1");

        list.add(1, 30);
        check(list.get(1) == 30, "add(index): element at size (1) should be 30");

        list.add(1, 20);
        check(list.get(0) == 10, "add(index): index 0 unchanged");
        check(list.get(1) == 20, "add(index): index 1 should be newly inserted 20");
        check(list.get(2) == 30, "add(index): index 2 should be shifted 30");
        check(list.size() == 3, "add(index): size should be 3");

        try {
            list.add(-1, 99);
            check(false, "add(index): Failed to throw exception for negative index");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "add(index): Threw exception for negative index");
        }

        try {
            list.add(5, 99);
            check(false, "add(index): Failed to throw exception for index > size");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "add(index): Threw exception for index > size");
        }
    }

    static void testGet(List<Integer> list) {
        reset(list);

        try {
            list.get(0);
            check(false, "get: Failed to throw exception on empty list");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "get: Threw exception on empty list");
        }

        list.addLast(10);
        list.addLast(20);

        check(list.get(0) == 10, "get: index 0 should be 10");
        check(list.get(1) == 20, "get: index 1 should be 20");

        try {
            list.get(-1);
            check(false, "get: Failed to throw exception for negative index");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "get: Threw exception for negative index");
        }

        try {
            list.get(2);
            check(false, "get: Failed to throw exception for index >= size");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "get: Threw exception for index >= size");
        }
    }

    // --- NEW: Test getFirst() and getLast() ---
    static void testGetFirstAndLast(List<Integer> list) {
        reset(list);

        try {
            list.getFirst();
            check(false, "getFirst: Failed to throw exception on empty list");
        } catch (Exception e) {
            check(true, "getFirst: Threw exception on empty list");
        }

        try {
            list.getLast();
            check(false, "getLast: Failed to throw exception on empty list");
        } catch (Exception e) {
            check(true, "getLast: Threw exception on empty list");
        }

        list.addLast(50);
        check(list.getFirst() == 50, "getFirst: Should be 50 with 1 element");
        check(list.getLast() == 50, "getLast: Should be 50 with 1 element (same as first)");

        list.addLast(99);
        check(list.getFirst() == 50, "getFirst: Should still be 50");
        check(list.getLast() == 99, "getLast: Should be 99");
    }

    static void testSet(List<Integer> list) {
        reset(list);

        try {
            list.set(0, 99);
            check(false, "set: Failed to throw exception on empty list");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "set: Threw exception on empty list");
        }

        list.addLast(5);
        list.addLast(15);

        list.set(0, 100);
        check(list.get(0) == 100, "set: index 0 should be updated to 100");
        check(list.get(1) == 15, "set: index 1 should remain 15");

        list.set(1, 200);
        check(list.get(1) == 200, "set: index 1 should be updated to 200");
        check(list.size() == 2, "set: size should remain 2");

        try {
            list.set(-1, 99);
            check(false, "set: Failed to throw exception for negative index");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "set: Threw exception for negative index");
        }

        try {
            list.set(2, 99);
            check(false, "set: Failed to throw exception for index >= size");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "set: Threw exception for index >= size");
        }
    }

    static void testRemove(List<Integer> list) {
        reset(list);

        try {
            list.remove(0);
            check(false, "remove(index): Failed to throw exception on empty list");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "remove(index): Threw exception on empty list");
        }

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        int removed = list.remove(1);
        check(removed == 20, "remove(index): removed value should be 20");
        check(list.size() == 2, "remove(index): size should be 2");
        check(list.get(1) == 30, "remove(index): element at index 1 should be 30");

        removed = list.remove(0);
        check(removed == 10, "remove(index): removed value should be 10");
        check(list.size() == 1, "remove(index): size should be 1");
        check(list.get(0) == 30, "remove(index): element at index 0 should be 30");

        removed = list.remove(0);
        check(removed == 30, "remove(index): removed value should be 30");
        check(list.isEmpty(), "remove(index): list should be empty after removing last element");

        list.addLast(50);
        try {
            list.remove(-1);
            check(false, "remove(index): Failed to throw exception for negative index");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "remove(index): Threw exception for negative index");
        }

        try {
            list.remove(1);
            check(false, "remove(index): Failed to throw exception for index >= size");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            check(true, "remove(index): Threw exception for index >= size");
        }
    }

    static void testRemoveFirstAndLast(List<Integer> list) {
        reset(list);

        try {
            list.removeFirst();
            check(false, "removeFirst: Failed to throw exception on empty list");
        } catch (Exception e) {
            check(true, "removeFirst: Threw exception on empty list");
        }

        try {
            list.removeLast();
            check(false, "removeLast: Failed to throw exception on empty list");
        } catch (Exception e) {
            check(true, "removeLast: Threw exception on empty list");
        }

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        int first = list.removeFirst();
        check(first == 10, "removeFirst: Should return 10");
        check(list.size() == 2, "removeFirst: Size should be 2");
        check(list.get(0) == 20, "removeFirst: New first element should be 20");

        int last = list.removeLast();
        check(last == 30, "removeLast: Should return 30");
        check(list.size() == 1, "removeLast: Size should be 1");
        check(list.get(0) == 20, "removeLast: Remaining element should be 20");
    }

    // --- NEW: Test indexOf() and contains() ---
    static void testIndexOfAndContains(List<Integer> list) {
        reset(list);

        check(list.indexOf(5) == -1, "indexOf: Should return -1 on empty list");
        check(!list.contains(5), "contains: Should return false on empty list");

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(20); // Add a duplicate

        check(list.indexOf(10) == 0, "indexOf: First element should be at index 0");
        check(list.indexOf(30) == 2, "indexOf: Element should be at index 2");
        check(list.indexOf(20) == 1, "indexOf: Duplicate element should return FIRST occurrence (index 1)");
        check(list.indexOf(99) == -1, "indexOf: Missing element should return -1");

        check(list.contains(10), "contains: Should return true for existing element");
        check(list.contains(30), "contains: Should return true for existing element");
        check(!list.contains(99), "contains: Should return false for missing element");
    }

    static void testSizeAndIsEmpty(List<Integer> list) {
        reset(list);

        check(list.isEmpty(), "isEmpty: should be true on fresh reset");
        check(list.size() == 0, "size: should be 0 on fresh reset");

        list.addLast(1);

        check(!list.isEmpty(), "isEmpty: should be false after add");
        check(list.size() == 1, "size: should be 1");

        list.remove(0);

        check(list.isEmpty(), "isEmpty: should be true after removing only element");
        check(list.size() == 0, "size: should be 0 after removing only element");
    }

    // --- NEW: Test clear() ---
    static void testClear(List<Integer> list) {
        reset(list);

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        list.clear();

        check(list.isEmpty(), "clear: isEmpty should be true after clear");
        check(list.size() == 0, "clear: size should be 0 after clear");

        try {
            list.get(0);
            check(false, "clear: Failed to throw exception when accessing element after clear");
        } catch (Exception e) {
            check(true, "clear: Accessing index 0 correctly throws exception after clear");
        }
    }

    static void testStress(List<Integer> list) {
        reset(list);

        int limit = 1000;

        for (int i = 0; i < limit; i++) {
            list.addLast(i);
        }
        check(list.size() == limit, "stress: size should be " + limit + " after mass addLast");
        check(list.get(limit / 2) == limit / 2, "stress: middle element check after mass addLast");

        for (int i = 0; i < limit; i++) {
            list.removeLast();
        }
        check(list.isEmpty(), "stress: list should be empty after mass removeLast");

        for (int i = 0; i < limit; i++) {
            list.addFirst(i);
        }
        check(list.size() == limit, "stress: size should be " + limit + " after mass addFirst");
        check(list.get(0) == limit - 1, "stress: first element should be " + (limit - 1));
        check(list.get(limit - 1) == 0, "stress: last element should be 0");
    }
}