package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 

	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}


	public void addFirstNode(Node<E> nuevo) {
		((SNode<E>) nuevo).setNext(first); 
		first = (SNode<E>) nuevo; 
		length++; 

	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext()); 
		((SNode<E>) target).setNext((SNode<E>) nuevo); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		//if first
		if (target == first)
			this.addFirstNode(nuevo); 
		//if only one
		if(first==last){
			this.addNodeAfter(first, nuevo);
		} 
		//other
		else { 
			SNode<E> curr = first; 
			while (((SNode<E>) curr).getNext() != target)
				curr = curr.getNext(); 
			this.addNodeAfter(curr, nuevo); 
		}
		length++;
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (first == null && last == null)
			throw new NoSuchElementException("getFirstNode() : linked list is empty..."); 
		return first;

	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (first == null && last == null)
			throw new NoSuchElementException("getLastNode(): Empty list."); 
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		if (first == null && last==null)
			throw new NoSuchElementException("getNodeAfter(): Empty list.");
		SNode<E> curr = first; 
		while (((SNode<E>) curr).getNext() != target)
			curr = curr.getNext(); 
		return curr; 

	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if (first == null && last == null)  
			throw new NoSuchElementException("getPrevNode(...) : target is the first node."); 
		SNode<E> curr = first; 
		while (((SNode<E>) curr).getNext() != target)
			curr = curr.getNext(); 
		return curr; 
	}

	public int length() {
		return this.length;
	}

	public void removeNode(Node<E> target) {
		//
		//set pointer y el node a remover a null
		//
		target = null;
	}

	public Node<E> createNewNode() {
		return new SNode<E>();
	}

}
