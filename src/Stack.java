public class Stack<T> {
	
	private LinkedList<T> list = new LinkedList<>();
	
	public void push (T ele) {
		list.addToTail(ele);
	}
	
	public void pop () throws IllegalArgumentException {
		if (isEmpty()) 
			throw new IllegalArgumentException ("There is no element in the stack");
		list.deleteFromTail();
	}
	
	public T topEl () throws IllegalArgumentException {
		if (isEmpty())
			throw new IllegalArgumentException ("There is no elemnt in the stack");
		return list.tail.data;
	}
	
	public boolean isEmpty () {
		return list.isEmpty();
	}
	
	public void clear () {
		if (isEmpty())
			return ;
		list.setToNull();
	}
	
	public String toString () {
		return list.toString();
	}
}
