/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst == null) {
            last = first;
        }
        else {
            oldfirst.prev = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (oldLast == null) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        n--;
        Item item = first.item;
        first = first.next;
        if (n == 0) {
            last = null;
        }
        else {
            first.prev = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        n--;
        Item item = last.item;
        last = last.prev;
        if (n == 0) {
            first = null;
        }
        else {
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing
    public static void main(String[] args) {
        // test 1 basic logic
        Deque<String> testList = new Deque<>();
        testList.addFirst("A");
        StdOut.println(testList.size());
        StdOut.println(testList.isEmpty());
        testList.addLast("B");
        StdOut.println(testList.size());
        StdOut.println(testList.removeFirst());
        StdOut.println(testList.removeLast());
        StdOut.println(testList.size());
        StdOut.println(testList.isEmpty());

        // test 2 shuffle intermixed calls
        Deque<Integer> intTest = new Deque<>();
        intTest.addFirst(1);
        intTest.addLast(2);
        intTest.addFirst(3);
        StdOut.println(intTest.removeFirst());
        StdOut.println(intTest.removeLast());
        StdOut.println(intTest.removeLast());
        StdOut.println(intTest.size());

        // test 3 iterator and foreach loop
        Deque<String> stringTest = new Deque<>();
        stringTest.addFirst("Uno");
        stringTest.addFirst("Dos");
        stringTest.addFirst("Tres");

        for (String string : stringTest) {
            for (String string2 : stringTest) {
                StdOut.println(string + ", " + string2);
            }
        }
    }
}
