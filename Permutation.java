import edu.princeton.cs.algs4.StdIn; 
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        // Check if the correct number of arguments is provided
        if (args.length != 1) {
            StdOut.println("Usage: java Permutation <k>");
            return;
        }

        try {
            int k = Integer.parseInt(args[0]);

            // Validate k (must be a positive integer)
            if (k <= 0) {
                StdOut.println("k must be a positive integer.");
                return;
            }

            // Create a RandomizedQueue to hold the strings
            RandomizedQueue<String> rq = new RandomizedQueue<>(); 

            // Read strings from standard input and enqueue them
            while (!StdIn.isEmpty()) {
                rq.enqueue(StdIn.readString());
            }

            // Dequeue and print the first k elements from the RandomizedQueue
            for (int i = 0; i < k; i++) {
                StdOut.println(rq.dequeue());
            }
        } catch (NumberFormatException e) {
            StdOut.println("Error: k must be an integer.");
        }
    }
}
