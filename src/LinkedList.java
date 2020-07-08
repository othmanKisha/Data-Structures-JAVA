class Node<T> {

    public T data;
    public Node<T> next;
    public Node<T> previous;

    public Node(T data) {
        this(data, null, null);
    }

    public Node() {
        this(null, null, null);
    }

    public Node(T data, Node<T> next, Node<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
}

public class LinkedList<T> {

    protected Node<T> head;
    protected Node<T> tail;
    protected int length = 0;

    public void addToHead(T ele) {
        Node<T> n = new Node<>(ele, head, null);
        head = n;
        if (tail == null)
            tail = head;
        length++;
    }

    public void addToTail(T ele) {
        if (tail != null) {
            tail = new Node<>(ele, null, tail);
            tail.previous.next = tail;
        } else
            head = tail = new Node<>(ele);
        length++;
    }

    public void deleteFromHead() {
        if (isEmpty())
            return;
        if (head == tail)
            head = tail = null;
        else {
            head = head.next;
            head.previous = null;
        }
        length--;
    }

    public void deleteFromTail() {
        if (isEmpty())
            return;
        if (head == tail)
            head = tail = null;
        else {
            tail = tail.previous;
            tail.next = null;
        }
        length--;
    }

    public void delete(T ele) {  
        if (!isEmpty()) {
            if (head == tail && ele.equals(head.data)) 
                 head = tail = null;       
            else if (ele.equals(head.data)) 
                 head = head.next;   
            else {                   
                 Node<T> pred, tmp;
                 for (pred = head, tmp = head.next; tmp != null && !tmp.data.equals(ele); pred = pred.next, tmp = tmp.next);
                 if (tmp != null) {   
                     pred.next = tmp.next;
                     if (tmp == tail) 
                        tail = pred;
                 }
            }
            length--;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printAll() {
        for (Node<T> tmp = head; tmp != null; tmp = tmp.next)
            System.out.print(tmp.data + "");
    }

    public boolean isInList(T el) {
        Node<T> tmp;
        for (tmp = head; tmp != null && !tmp.data.equals(el); tmp = tmp.next)
            ;
        return tmp != null;
    }

    public void setToNull() {
        head = tail = null;
    }

    public void selectionSort() {
        if (isEmpty())
            return;

        Node<T> tmp = new Node<>();
        Node<T> largest = new Node<>();
        LinkedList<T> large = new LinkedList<>();
        largest.data = head.data;
        int end = length - 1;

        for (int i = 0; i < end; i++) {
            for (tmp = head; tmp != null; tmp = tmp.next)
                if ((Integer) tmp.data > (Integer) largest.data)
                    largest.data = tmp.data;

            large.addToHead(largest.data);
            delete(largest.data);
            largest.data = head.data;
        }

        for (tmp = large.head; tmp != null; tmp = tmp.next)
            addToHead(tmp.data);
    }

    public String toString() {
        String s = "";
        Node<T> tmp = new Node<>();
        for (tmp = head; tmp.next != null; tmp = tmp.next) {
            s += tmp.data.toString();
            if (tmp.next.next == null) {
                s += tmp.next.data.toString();
            }
        }
        return s;
    }
}