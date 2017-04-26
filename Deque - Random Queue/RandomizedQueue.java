import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] ranq;
    private int n;// number of elements

    // construct an empty randomized queue
    public RandomizedQueue() {
        ranq = (Item[]) new Object[1];
        n = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the queue
    public int size() {
        return this.n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (n == ranq.length)
            resize(2 * ranq.length);
        ranq[n] = item;
        n++;
    }

    private void resize(int len) {
        Item[] tmp = (Item[]) new Object[len];
        for (int i = 0; i < n; i++) {
            tmp[i] = ranq[i];
        }
        ranq = tmp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (n > 0 && n == ranq.length / 4)
            resize(ranq.length / 2);
        int rand = StdRandom.uniform(n);
        // delete from the end
        Item item = ranq[rand];
        ranq[rand] = ranq[n - 1];
        ranq[n - 1] = null;
        n--;

        return item;
    }

    // return but do not remove a random item
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return ranq[StdRandom.uniform(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        // StdOut.println("enter iterator");
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private int index;
        private int[] tmp;

        public RandomIterator() {
            // StdOut.println("enter RandomIterator constructor");
            // TODO Auto-generated constructor stub
            index = 0;
            // use int[] to store the random index, instead of the random object to save the memory
            tmp = new int[n];
            for (int i = 0; i < n; i++) {
                tmp[i] = i;
            }
            // shuffle to get the random index
            StdRandom.shuffle(tmp);
        }

        public void remove() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            // StdOut.println("enter has next");
            // TODO Auto-generated method stub
            return index < n;
        }

        @Override
        public Item next() {
            // StdOut.println("enter next");
            // TODO Auto-generated method stub
            if (!hasNext())
                throw new NoSuchElementException();
            // ranq store the item, we use tmp to get the random index
            Item item = ranq[tmp[index]];
            index++;
            return item;
        }

    }
/**
    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        // while(!StdIn.isEmpty()){
        // String item=StdIn.readString();
        // if(!item.equals("-")) queue.enqueue(item);
        // else if (!queue.isEmpty()) {
        // StdOut.print(queue.sample()+" ");
        // }
        // StdOut.println(queue.size()+"left on queue");
        // }
        // while(StdIn.hasNextChar()){
        // String item=StdIn.readString();
        // queue.enqueue(item);
        // StdOut.println(queue.size()+"left on queue");
        // }
        // for(String s:queue){
        //
        // StdOut.println("s"+s);
        // }
        queue.enqueue("5");
        queue.sample();
        queue.dequeue();

        queue.enqueue("4");
        queue.sample();
        queue.dequeue();
        queue.enqueue("3");
        queue.sample();
        queue.dequeue();
        queue.enqueue("2");
        queue.sample();
        queue.dequeue();
        queue.enqueue("1");
        queue.sample();
        queue.dequeue();
        // Iterator<String> iterator =queue.iterator();
        // StdOut.println("before while");
        // while(iterator.hasNext()){
        // String string=iterator.next();
        // StdOut.println(string);
        // }

    }
    */
}
