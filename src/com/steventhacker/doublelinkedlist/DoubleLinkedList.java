package com.steventhacker.doublelinkedlist;

/**
 * Custom implementation of a Double Linked List. Uses an abstract Node to hold all data, each with
 * their own reference to the previous and next nodes in the list.
 */
@SuppressWarnings("unused")
public class DoubleLinkedList<E>
{
	private Node firstNode;
	private int size;

	/**
	 * Retrieve the current size of the list
	 * @return size of list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Return current state of list's size, whether it has values or not
	 * @return is list currently empty
	 */
	public boolean isEmpty() {
		return  size == 0;
	}

	/**
	 * Add the given element to the end of the list.
	 * @param e given element
	 */
	@SuppressWarnings("unchecked")
	public void add(E e) {
		Node newNode = new Node(e);

		if (firstNode == null) {
			firstNode = newNode;
			size++;
		} else {
			Node temp = firstNode.getNext();
			Node<E> prev = firstNode;

			while (temp != null) {
				prev = temp;
				temp = temp.getNext();
			}
			temp = newNode;
			prev.setNext(temp);
			temp.setPrev(prev);
			size++;
		}
	}

	/**
	 * Get the requested element from the list. If the list is null, null is returned. If the requested index is larger
	 * than the size of the list, IndexOutOfBoundsException is thrown. If the value is not found, null is returned
	 * @param index location in list of requested value
	 * @return value at requested index
	 */
	@SuppressWarnings("unchecked")
	public E get(int index) {
		if (firstNode == null) {
			return null;
		}
		if (index == 0) {
			return (E) firstNode.getData();
		}
		if (index > size) {
			throw new IndexOutOfBoundsException();
		}

		Node temp = firstNode.getNext();
		int count = 1;
		while (count <= index) {
			if (temp == null) {
				return null;
			} else if (count == index) {
				return (E) temp.getData();
			}
			temp = temp.getNext();
			count++;
		}
		return null;
	}

	/**
	 * Set the given element in the list at the given location. If the list is null then an IndexOutOfBoundsException
	 * is thrown. If the given index is outside of the list's size, an IndexOutOfBoundsException exception is thrown.
	 * @param index location in list that element will be set at
	 * @param element given element
	 */
	@SuppressWarnings("unchecked")
	public void set(int index, E element) {
		if (firstNode == null || index > size) {
			// If the list is empty and the user is trying to set a location, or if
			// the user is trying to set to a location outside of list, throw exception
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			firstNode.setData(element);
		}

		Node temp = firstNode.getNext();
		Node prev = firstNode;
		int count = 1;
		while (count <= index) {
			if (temp == null) {
				throw new IndexOutOfBoundsException();
			}
			if (count == index) {
				temp.setData(element);
				temp.setNext(temp.getNext());
				temp.setPrev(prev);
				prev.setNext(temp);
			}
			prev = temp;
			temp = temp.getNext();
		}
	}

	/**
	 * Remove the given element from the list if it is currently present.
	 * @param e given element
	 */
	public void remove(E e) {
		if (firstNode != null) {
			if (firstNode.equals(e)) {
				firstNode = firstNode.getNext();
			} else {
				Node temp = firstNode;
				Node prev = firstNode;
				while (temp != null) {
					if (temp.equals(e)) {
						Node newNext = temp.getNext();
						prev.setNext(newNext);
						newNext.setPrev(prev);
						size--;
					}
					prev = temp;
					temp = temp.getNext();
				}
			}

		}
	}

	/**
	 * Find the first location in the list that contains the requested object.
	 * @param element object being requested
	 * @return first location in list containing object. -1 is returned if object is not found
	 */
	public int indexOf(E element) {
		int index = -1;

		Node temp = firstNode;

		for (int i = 0; i < size; i++) {
			if (temp.getData().equals(element)) {
				index = i;
				break;
			}
			temp = temp.getNext();
		}
		return index;
	}

	/**
	 * Find the location of the list that contains the last instance of the object passed in
	 * @param element object to find last location of
	 * @return last location of requested object in list. -1 is returned if object is not found
	 */
	public int lastIndexOf(E element) {
		int index = -1;

		Node temp = firstNode;

		for (int i = 0; i < size; i++) {
			if (temp.getData().equals(element)) {
				index = i;
			}
			temp = temp.getNext();
		}
		return index;
	}

	/**
	 * Convert to an object array
	 * @return array contains all elements in list
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];

		Node temp = firstNode;
		for (int i = 0; i < size; i++) {
			array[i] = temp.getData();
			temp = temp.getNext();
		}
		return array;
	}

	/**
	 * Check if the list currently contains the given object. If list is empty, false is returned
	 * @param o given object being checked for
	 * @return true iff list currently contains the object passed in
	 */
	public boolean contains(Object o) {
		if (o == null) {
			return false;
		}

		Node temp = firstNode;
		while (temp != null) {
			if (o.equals(temp.getData())) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	/**
	 * Clear the list, removing all elements and setting size to 0
	 */
	public void clear() {
		while (firstNode != null) {
			Node temp = firstNode.getNext();
			firstNode.clear();
			firstNode = temp;
		}
		size = 0;
	}
}
