package unb.utils;

import java.util.ArrayList;
import java.util.List;

import unb.modules.process.scheduling.readyQueue;

public class ManagerQueue<T> {
	private List<T> queue = new ArrayList<T>();

	public int size() {
		return this.queue.size();
	}
	
	public void next() {
		// remove o primeiro da fila e manda para o fim da fila
		// E avança o resto da fila
		T head = this.remove(0);
		this.insert(head);		
	}

	public T get(int index) {
		T head =  this.queue.get(index);
		return head;
	}

	public T remove(int index) {
		T head =  this.queue.get(index);
		this.queue.remove(index);
		
		return head;
	}

	public void insert(T obj) {
		this.queue.add(obj);
	}


	public boolean contains(T obj) {
		return this.queue.contains(obj);
	}
	
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	public List<T> toList() {
		return this.queue;
	}

}
