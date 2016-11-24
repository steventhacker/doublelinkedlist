package com.steventhacker.doublelinkedlist;

/**
 * Container to hold data in a singly linked list format, with a reference to the next node in succession.
 */
public class Node<E> {
	private E data;
	private Node prev;
	private Node next;

	public Node(E data) {
		this.data = data;
	}

	public Node(E data, Node prev, Node next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node getPrev()
	{
		return prev;
	}

	public void setPrev(Node prev)
	{
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public void clear() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}
}
