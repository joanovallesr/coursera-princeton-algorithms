/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;
    private Item[] s;

    // construct an empty randomized queue
    public RandomizedQueue() {
        N = 0;
        s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of times on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (N == s.length) {
            resize(2 * s.length);
        }
        s[N++] = item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniformInt(N);
        Item item = s[randomIndex];
        s[randomIndex] = s[N - 1];
        s[N - 1] = null;
        N--;
        if (N == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        return s[StdRandom.uniformInt(N)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        private Item[] copy;

        public ReverseArrayIterator() {
            copy = (Item[]) new Object[N];
            for (int j = 0; j < N; j++) {
                copy[j] = s[j];
            }
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy[--i];
        }
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        rq.enqueue("D");

        // test 1 print size (should be 4)
        StdOut.println(rq.size());

        // test 2 print if it's empty (should be false)
        StdOut.println(rq.isEmpty());

        // test 3 iterator
        for (String s : rq) {
            StdOut.println(s + " ");
        }
        StdOut.println();

        // test 4 independence (print should be different from above
        for (String s : rq) {
            StdOut.println(s + " ");
        }
        StdOut.println();

        // test 5 sample (should likely be different but size shouldn't change
        StdOut.println(rq.sample());
        StdOut.println(rq.sample());

        // test 6 empty array
        while (!rq.isEmpty()) {
            StdOut.println(rq.dequeue() + " ");
        }

        // test 7 verify emptiness (should be true and size 0)
        StdOut.println(rq.isEmpty());
        StdOut.println(rq.size());
    }
}
