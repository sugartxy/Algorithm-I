import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private int n;// size of Deque
    private Node first; // top of link
    private Node last; // end of link
    
    // linked list class
    private class Node{
        private Item item;
        // double linked list, so we can traverse 
        // from end to start and from start to end
        private Node next;
        private Node before;
    }
    
    // construct an empty deque
    public Deque(){
        n=0;
        first=null;
        last=null;
        
    }
    // is the deque empty?
    public boolean isEmpty(){
        return first == null;
    }
    // return the number of items on the deque
    public int size(){
        return n;
    }
    // add the item to the front
    public void addFirst(Item item){
        if(item==null) throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item=item;
        // double linked list need to modify next and before
        first.next=oldFirst;
        first.before=null; 
        // corner check. if add a node to an empty deque
        if(oldFirst==null) last=first;
        else oldFirst.before=first;
        n++;
    }
    // add the item to the end
    public void addLast(Item item){
        if(item==null) throw new NullPointerException();
        Node oldLast = last;
        last=new Node();
        last.item=item;
        // double linked list need to modify next and before
        last.next=null;
        last.before=oldLast;
        // corner check
        if(isEmpty()) first=last;
        else oldLast.next=last;
        n++;
        
    }
    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException("Deque removeFirst underflow");
        Item item =first.item;
        first=first.next;
        // double linked list need to modify next and before
        // corner case
        if(isEmpty()) last=null;
        else first.before=null;
        n--;
        return item;
    }
    // remove and return the item from the end
    public Item removeLast(){
        if(isEmpty()) throw new NoSuchElementException("Deque removeLast underFlow");
        Item item=last.item;
        last=last.before;
        // double linked list need to modify next and before
        // corner case
        if(last==null) first=null;
        else last.next=null;
        n--;
        return item;
    }
    // return and iterator over items in order from front to end
    public Iterator<Item> iterator(){
        
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
        private Node node=first;
        public boolean hasNext(){
            return node!=null;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            Item item=node.item;
            node=node.next;
            return item;
        }
    }
    // unit testing
//    public static void main(String args[]){
//        Deque<String> deque=new Deque<String>();
//        deque.addLast("0");
//        System.out.println(deque.removeLast());
//        System.out.println(deque.size());
//        System.out.println(deque.isEmpty());
//        deque.addFirst("5");
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.isEmpty());
//        deque.addLast("7");
//        deque.addFirst("A");
//        deque.addLast("B");
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
//        for(int i=0;i<10;i++){
//            deque.addLast(""+i);
//            System.out.println(deque.size());
//        }
//        for(String s:deque){
//            System.out.println(s);
//            System.out.println(deque.size());
//        }
//        
//    }
    
}
