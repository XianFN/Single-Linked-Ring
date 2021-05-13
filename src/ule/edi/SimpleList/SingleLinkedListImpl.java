package ule.edi.SimpleList;


import java.util.Iterator;
import java.util.NoSuchElementException;



//import ule.edi.recursive.Node;
//import ule.edi.recursive.SingleLinkedListImpl.iteratorSimple;

//import ule.edi.recursive.Node;

public class SingleLinkedListImpl<T> extends AbstractSingleLinkedListImpl<T> {

	public SingleLinkedListImpl(T... elements) {

		for (T el : elements) {
			addLast(el);
		}

	}

	@Override
	public void addLast(T element) {
		if (header == null) {

			header = new Node<T>(element);

		} else {

			addToRearRecursive(element, header);
		}
	}

	private void addToRearRecursive(T el, Node<T> node) {

		if (node.next == null) {

			node.next = new Node<T>(el);

		} else {

			addToRearRecursive(el, node.next);
		}
	}

	@Override
	public Iterator<T> iterator() {
		// Auto-generated method stub
		return new iteratorSimple();
	}

	private class iteratorSimple implements Iterator<T> {

		private Node<T> auxHeader = header;

		@Override
		public boolean hasNext() {
			// Auto-generated method stub
			return auxHeader != null;
		}

		@Override
		public T next() {
			// Auto-generated method stub

			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T element = auxHeader.content;
			auxHeader = auxHeader.next;
			return element;
		}

		// La operacion remove no esta soportada en esta coleccion
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public boolean isEmpty() {

		return header == null;
	}

	@Override
	public int size() {

		if (isEmpty()) {
			return 0;
		}
		return recursivoSize(0, header);

	}

	private int recursivoSize(int contador, Node<T> aux) {
		if (aux.next == null) {

			contador = 1;
		} else {

			contador = 1 + recursivoSize(contador, aux.next);
		}
		return contador;
	}

	@Override
	public void addFirst(T element) {
		Node<T> aux = new Node<T>(element);
		aux.next = header;
		header = aux;
	}

	@Override
	public void addAtPos(T element, int p) {
		if (p < 0) {
			throw new IllegalArgumentException("");
		}
	
		if (p > size()) {

			addLast(element);

		} else {

			addAtPosRecursive(element, p, 1, header);
		}
	}

	private int addAtPosRecursive(T element, int posicion, int contador, Node<T> aux) {

		if (posicion == 1) {
			// entra en la cabezera
			Node<T> nuevo = new Node<T>(element);
			nuevo.next = header;
			header = nuevo;
		} else {
			if (contador == posicion - 1) {
				Node<T> nuevo = new Node<T>(element);

				nuevo.next = aux.next;
				aux.next = nuevo;

			} else {

				addAtPosRecursive(element, posicion, contador + 1, aux.next);
			}
		}
		return contador;

	}

	@Override
	public void addNTimes(T element, int n) {
		if (n < 0) {
			throw new IllegalArgumentException("");
			
		} else {

			addNTimesRecursive(element, n, 0);
		}
	}

	private int addNTimesRecursive(T element, int posicion, int contador) {

		if (contador == posicion) {

		} else {
			addLast(element);

			addNTimesRecursive(element, posicion, contador + 1);
		}

		return contador;

	}

	@Override
	public int indexOf(T elem) {

		return indexOfRecursive(elem, 1, header);

	}

	private int indexOfRecursive(T element, int contador, Node<T> aux) {

		if (aux == null) {
			throw new NoSuchElementException();

		} else {
			if (aux.content.equals(element)) {
				return contador;
			} else {

				return indexOfRecursive(element, contador + 1, aux.next);
			}
		}

	}

	@Override
	public T removeLast() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("");
		}
		if (size() == 1) {
			T ele = header.content;
			header = null;
			return ele;
		} else {
			return recursivoRemoveLast(header);
		}

	}

	private T recursivoRemoveLast(Node<T> aux) {
		if (aux.next.next == null) {
			T ele = aux.next.content;

			aux.next = null;
			return ele;

		} else {

			return recursivoRemoveLast(aux.next);
		}

	}

	@Override
	public T removeLast(T elem) throws EmptyCollectionException, NoSuchElementException {
		if (isEmpty()) {
			throw new EmptyCollectionException("");
		}
		if (size() == 1) {
			if (header.content != elem) {
				throw new NoSuchElementException("");
			}
			T ele = header.content;
			header = null;
			return ele;
		} else {
			return recursivoRemoveLastWithElement(header, elem, null, null);
		}

	}

	private T recursivoRemoveLastWithElement(Node<T> aux, T ele, Node<T> nodoConElelemento,
			Node<T> nodoConElelementoanterior) {

		if (aux.next == null) {
			if (nodoConElelemento == null) {
				throw new NoSuchElementException();
			} else {
				nodoConElelementoanterior.next = nodoConElelemento.next;
				return nodoConElelemento.content;
			}

		} else {
			if (aux.next.content == ele) {
				nodoConElelemento = aux.next;
				nodoConElelementoanterior = aux;
			}

			return recursivoRemoveLastWithElement(aux.next, ele, nodoConElelemento, nodoConElelementoanterior);
		}

	}

	@Override
	public AbstractSingleLinkedListImpl<T> reverse() {
		SingleLinkedListImpl<T> reverseList = new SingleLinkedListImpl<T>();

		return recursiveReverse(this.header, reverseList);

	}

	private AbstractSingleLinkedListImpl<T> recursiveReverse(Node<T> header1, SingleLinkedListImpl<T> reverseList) {

		if (header1.next == null) {

			reverseList.addFirst(header1.content);
			return reverseList;
		} else {

			reverseList.addFirst(header1.content);

			return recursiveReverse(header1.next, reverseList);
		}

	}

	@Override
	public int isSubList(AbstractSingleLinkedListImpl<T> part) {

		Iterator<T> itSub = part.iterator();// Iterador de la sublista
		if (part.size()==0) {
			return 1;
		}
		return recursiveisSubList(1, header, false, 0, itSub, part);
	}

	private int recursiveisSubList(int contador, Node<T> aux, boolean flag, int ret, Iterator<T> itSub,
			AbstractSingleLinkedListImpl<T> part) {
		
		if (aux==null) {
			return -1;
		}

		if (itSub.next().equals(aux.content)) {
			flag = true;
			ret++;
			if (ret==part.size()) {
				return contador-ret+1;
			}

		} else {
			
				flag = false;
				ret = 0;
				itSub = part.iterator();
			}

		return recursiveisSubList(contador+1, aux.next, flag, ret, itSub, part);

	}
}
