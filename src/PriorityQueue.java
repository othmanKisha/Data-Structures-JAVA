public class PriorityQueue<T> {

	private LinkedList<T> list = new LinkedList<>();

	public void enqueue(T ele) {
		list.addToTail(ele);
		list.selectionSort();
	}

	public void dequeue() throws IllegalArgumentException {
		if (isEmpty())
			throw new IllegalArgumentException("There is no element in the stack");
		list.deleteFromHead();
	}

	public T firstEl() throws IllegalArgumentException {
		if (isEmpty())
			throw new IllegalArgumentException("There is no elemnt in the stack");
		return list.head.data;
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void clear() {
		if (isEmpty())
			return;
		list.setToNull();
	}

	public String toString() {
		return list.toString();
	}
}
