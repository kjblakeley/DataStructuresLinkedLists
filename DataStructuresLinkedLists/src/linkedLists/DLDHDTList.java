package linkedLists;

import java.util.NoSuchElementException;

import linkedLists.AbstractSLList.SNode;

public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
		// ADD CODE HERE to generate empty linked list of this type 
		header = null; 
		trailer = null;
		length = 0; 
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// ADD CODE HERE
		//if first
		if (target == header)
			this.addFirstNode(nuevo); 
		//if only one
		if(header==trailer){
			this.addNodeAfter(header, nuevo);
		} 
		//other
		else { 
			DNode<E> curr = header; 
			while (((DNode<E>) curr).getNext() != target)
				curr = curr.getNext(); 
			this.addNodeAfter(curr, nuevo); 
		}
		length++;
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		if (header == null && trailer==null)
			throw new NoSuchElementException("getNodeAfter(): Empty list.");
		DNode<E> curr = header; 
		while (((DNode<E>) curr).getNext() != target)
			curr = curr.getNext(); 
		return curr; 
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		if (header == null && trailer == null)  
			throw new NoSuchElementException("getPrevNode(...) : target is the first node."); 
		DNode<E> curr = header; 
		while (((DNode<E>) curr).getNext() != target)
			curr = curr.getNext(); 
		return curr; 	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		// ADD CODE HERE to disconnect target from the linked list, reduce lent, clean target...
		if (target == header) 
			header = header.getNext(); 
		else { 
			DNode<E> prevNode = (DNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((DNode<E>) target).getNext()); 
		}
		((DNode<E>) target).clean();   // clear all references from target
		length--; 
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		// TODO
	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}

}
