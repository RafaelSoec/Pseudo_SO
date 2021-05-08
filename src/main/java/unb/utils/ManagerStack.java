package unb.utils;

import java.util.ArrayList;
import java.util.List;

public class ManagerStack<T> {
	private List<T> stack = new ArrayList<T>();

	public int size() {
		return this.stack.size();
	}

	public T pop() {
		int max = this.size() - 1;
		T head =  this.stack.get(max);
		this.stack.remove(max);
		
		return head;
	}

	public void push(T obj) {
		this.stack.add(obj);
	}

	public boolean isEmpty() {
		return this.stack.isEmpty();
	}
}
