import java.util.Iterator; 
import java.util.NoSuchElementException; 

import edu.princeton.cs.algs4.StdIn; 
import edu.princeton.cs.algs4.StdOut; 


public class Deque<Item> implements Iterable<Item> {

    private Node fir = null; 
    private Node las = null; 
    private int size = 0; 


    private class Node{
        private Item item; 
        private Node next; 
        private Node previous; 
    }

    // construct an empty deque
    public Deque(){

    }

    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;  // removed unnecessary parentheses
    }

    // return the number of items on the deque
    public int size(){
        return size; 
    }

    // add the item to the front
    public void addFirst(Item item){
        validateItem(item); 
        Node oldFirst = fir; 
        fir = new Node(); 
        fir.item = item; 
        fir.next = oldFirst; 
        fir.previous = null; 

        if(isEmpty()){
            las = fir; 
        } else {
            oldFirst.previous = fir; 
        }
        size++; 
    }

    // add the item to the back
    public void addLast(Item item){
        validateItem(item); 
        Node oldLast = las; 
        las = new Node(); 
        las.item = item; 
        las.next = oldLast; 
        if(isEmpty()){
            fir = las;
        } else {
            oldLast.next = las;  // fixed typo: 'last' -> 'las'
        }
        size++; 
    }

    // remove and return the item from the front
    public Item removeFirst(){
        validateList(); 
        Item item = fir.item; 
        size--;
        if(isEmpty()){
            fir = null; 
            las = null; 
        } else {
            fir = fir.next; 
            fir.previous = null; 
        }
        return item; 
    }

    // remove and return the item from the back
    public Item removeLast(){
        validateList(); 
        Item item = las.item; 
        size--; 
        if(isEmpty()){
            fir = null; 
            las = null;
        } else {
            las = las.previous; 
            las.next = null;  // this line could be removed, but it doesn't harm functionality
        }
        return item; 
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator(); 
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = fir; 

        public boolean hasNext(){
            return current != null; 
        }

        public void remove(){
            throw new UnsupportedOperationException(); 
        }

        public Item next() {
            if(!hasNext()){
                throw new NoSuchElementException(); 
            } else {
                Item item = current.item; 
                current = current.next; 
                return item; 
            }
        }
    }

    private void validateList(){
        if(isEmpty()){
            throw new NoSuchElementException("Can't remove, empty list"); // fixed typo: 'NoSuchElementExcpetion' -> 'NoSuchElementException'
        }
    }

    private void validateItem(Item item){
        if(item == null){
            throw new NullPointerException("Can't add null pointer"); 
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<String> deque = new Deque<>();  // fixed: 'new deque<>()' -> 'new Deque<>()'
        while(!StdIn.isEmpty()){
            String s = StdIn.readString(); 
            if(s.equals("-"))
                StdOut.print(deque.removeLast());
            else 
                deque.addFirst(s); 
        }
    }
}
