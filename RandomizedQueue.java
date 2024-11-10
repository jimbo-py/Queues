import java.util.Iterator; 
import java.util.NoSuchElementException; 

import edu.princeton.cs.algs4.StdIn; 
import edu.princeton.cs.algs4.StdOut; 
import edu.princeton.cs.algs4.StdRandom;  // Corrected import

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items = (Item[]) new Object[2]; 
    private int size = 0; 

    // construct an empty randomized queue
    public RandomizedQueue() {}

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0; 
    }

    // return the number of items on the randomized queue
    public int size() {
        return size; 
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {  // Corrected comparison operator
            throw new NullPointerException("Can't enqueue null item");
        }
        this.items[size++] = item; 
        if (size == this.items.length) {
            resize(2 * this.items.length); 
        }
        swapItem();  // Corrected method name
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Can't dequeue, queue is empty");  // Fixed typo in message
        }
        Item item = this.items[size - 1];  // Fixed index access
        size--; 
        if (size > 0 && size == this.items.length / 4) {
            resize(this.items.length / 2); 
        }
        this.items[size] = null; 
        return item; 
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Can't sample, queue is empty");  // Fixed exception message
        }
        int i = StdRandom.uniform(size);
        return this.items[i];
    }

    // return an iterator over items
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int i = 0;  // Ensure index starts from 0

        public boolean hasNext() {
            return i < size;  // Fixed iterator logic
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Item item = items[i++];
                return item;
            }
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    private void swapItem() {
        if (size > 0) {  // Added safety check
            int i = StdRandom.uniform(size);
            Item temp = items[i];
            items[i] = items[size - 1];
            items[size - 1] = temp;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(rq.dequeue());
            else
                rq.enqueue(s);
        }
    } // unit testing (optional)
}

