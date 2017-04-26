import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        // use args instead of readInt, k belongs to the commond line
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        // cannot get out of while if use StdIn.isEmpty()
        String[] strings = StdIn.readAllStrings();
        StdRandom.shuffle(strings);
        // if use i<strings.length, there is a memory test fail
        for (int i = 0; i < k; i++) {
            queue.enqueue(strings[i]);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }

    }
}
