import java.util.*;

/**
 * SingleLinkedList implementation of IndexedUnsortedList interface
 * 
 * @param <T> - type of elements held in this collection
 * @author The Boobies
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

    /**
     * List and modification counters
     * @var int
     */
    private int count, modCount;

    /**
     * Head and tail nodes
     * @var Node<T>
     */
    private Node<T> head, tail;

    /**
     * Class constructor
     */
    public IUDoubleLinkedList() {
        count = modCount = 0;
        head = tail = null;
    }

    /**
     * Adds an element to the front of the list
     * @param   T   element     element to add to the front of the list
     * @return  void
     */
    @Override
    public void addToFront(T element) {
        Node<T> newNode = new Node<T>(element);

        if (head == null) {
            newNode.setPrevious(null);
            newNode.setNext(null);
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }

        count++;
        modCount++;
    }

    /**
     * Adds an element to the end of the list
     * @param   T   element     element to add to the end of the list
     * @return  void
     */
    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<T>(element);

        newNode.setNext(null);
        Node<T> current = head;

        if (head == null) {
            newNode.setPrevious(null);
            newNode.setNext(null);
            head = tail = newNode;
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(newNode);
            newNode.setPrevious(current);
        }

        count++;
        modCount++;
    }

    /**
     * Adds an element to the list
     * Adding an element using this method, the element gets added to the end of the list
     * @param   T   element     element to add to the list
     * @return  void
     */
    @Override
    public void add(T element) {
        addToRear(element);
    }

    /**
     * Adds an element after a target element into the list
     * @param   T   element     the element to add after target location
     * @param   T   target      target element to add element after
     * @throws  NoSuchElementException  thrown if the list is currently empty or target value is invalid
     * @return  void
     */
    @Override
    public void addAfter(T element, T target) {
        if (this.isEmpty() || (int)target < 0) {
            throw new NoSuchElementException();
        }

        Node<T> current = head;
        Node<T> newNode = new Node<T>(element);

        while (current.getNext() != null) {
            if (current.getElement().equals(target)) {
                newNode.setPrevious(current);
                newNode.setNext(current.getNext());
                current.setNext(newNode);

                if (newNode.getNext() != null) {
                    newNode.getNext().setPrevious(newNode);
                }
            }

            current = current.getNext();
        }

        count++;
        modCount++;
    }

    /**
     * Adds an element to a specific index in the list
     * @param   int     index   index to add element to inside the list
     * @param   T       element the element to add into the specified index
     * @throws  IndexOutOfBoundsException   thrown if the specified index is out of range
     * @return  void
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            addToFront(element);
        } else if (index == count) {
            addToRear(element);
        } else {
            Node<T> newNode = new Node<T>(element);
            Node<T> current = head;

            for (int i = 1; i < index; i++) {
                current = current.getNext();
            }

            newNode.setNext(current.getNext());
            current.setNext(newNode);
            newNode.setPrevious(current);
            newNode.getNext().setPrevious(newNode);

            count++;
            modCount++;
        }
    }

    /**
     * Removes the first element in the list and also returns the removed element
     * @throws NoSuchElementException   thrown if the list is currently empty
     * @return  T   first element in the list
     */
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        T result = null;

        if (head.getNext() == null) {
            result = head.getElement();
            head = null;
            tail = null;
        } else {
            result = head.getElement();
            head = head.getNext();
        }

        count--;
        modCount++;

        return result;
    }

    /**
     * Removes the last element in the list and also returns the removed element
     * @throws NoSuchElementException   thrown if the list is currently empty
     * @return  T   last element in the list
     */
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        T result = null;

        if (head.getNext() == null) {
            result = head.getElement();
            head = null;
            tail = null;
        } else {
            Node<T> current = head;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            Node<T> lastNode = current;
            result = lastNode.getElement();
            Node<T> previous = current.getPrevious();
            previous.setNext(null);
            previous.setPrevious(current.getPrevious().getPrevious());
        }

        count--;
        modCount++;

        return result;
    }

    /**
     * Removes a specified element from the list and also returns the removed element
     * @param   T   element     the element to remove and return from the list
     * @throws  NoSuchElementException  thrown if a specified element does not exist in the list
     * @return  T   element removed from the list
     */
    @Override
    public T remove(T element) {
        if (this.isEmpty() || (int)element < 0) {
            throw new NoSuchElementException();
        }

        T result = null;

        if (head.getNext() == null) {
            result = head.getElement();
            head = null;
            tail = null;
        } else {
            Node<T> current = head;
            
            while (current.getNext() != null) {
                if (current.getElement().equals(element)) {
                    break;
                }

                current = current.getNext();
            }

            result = current.getElement();

            if (current.getPrevious() == null) {
                if (current.getNext() == null) {
                    head = null;
                    tail = null;
                } else {
                    removeFirst();
                }
            } else {
                Node<T> previousNode = null;
                Node<T> nextNode = null;

                if (current.getNext() == null) {
                    previousNode = current.getPrevious();
                    nextNode = current;
                } else {
                    previousNode = current.getPrevious();
                    nextNode = current.getNext();
                }

                previousNode.setNext(nextNode);
                nextNode.setPrevious(previousNode);
            }
        }

        if (result == null) {
            throw new NoSuchElementException();
        }

        count--;
        modCount++;

        return result;
    }

    /**
     * Removes an element from the list at the specified index and also returns the removed item
     * @param   int     index   index at which to remove an element from the list
     * @throws  IndexOutOfBoundsException   thrown if specified index is out of range
     * @return  T   element removed from the list
     */
    @Override
    public T remove(int index) {
        if (this.isEmpty() || (index < 0 || index > count - 1)) {
            throw new IndexOutOfBoundsException();
        }

        T result = null;
        Node<T> current = head;

        for (int i = 0; i < count; i++) {
            if (i == index) {
                break;
            }

            current = current.getNext();
        }

        result = current.getElement();

        if (current.getPrevious() == null) {
            if (current.getNext() == null) {
                head = null;
                tail = null;
            } else {
                removeFirst();
            }
        } else {
            Node<T> previousNode = null;
            Node<T> nextNode = null;

            if (current.getNext() == null) {
                previousNode = current.getPrevious();
                nextNode = current;
            } else {
                previousNode = current.getPrevious();
                nextNode = current.getNext();
            }

            previousNode.setNext(nextNode);
            nextNode.setPrevious(previousNode);
        }

        count--;
        modCount++;

        return result;
    }

    /**
     * Sets a specified index in the list to the specified element
     * @param   int     index   index in the list to assign element to
     * @param   T       element the element to assign to the specified index
     * @throws  IndexOutOfBoundsException   thrown if the specified index is out of range
     * @return  void
     */
    @Override
    public void set(int index, T element) {
        if (this.isEmpty() || (index < 0 || index > count - 1)) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head;

        for (int i = 0; i < count; i++) {
            if (i == index) {
                current.setElement(element);
                break;
            } else {
                current = current.getNext();
            }
        }

        modCount++;
    }

    /**
     * Gets the element at the specified index in the list
     * @param   int     index   index in list to return element from
     * @throws  IndexOutOfBoundsException   thrown if the specified index is out of range
     * @return  T   element residing at the specified index
     */
    @Override
    public T get(int index) {
        if (this.isEmpty() || (index < 0 || index > count - 1)) {
            throw new IndexOutOfBoundsException();
        }

        T result = null;
        Node<T> current = head;

        for (int i = 0; i < count; i++) {
            if (i == index) {
                result = current.getElement();
                break;
            } else {
                current = current.getNext();
            }
        }

        return result;
    }

    /**
     * Returns the index of the specified element within the list
     * This method returns -1 in the case that the list does not contain the specified element
     * @param   T   element     element in which to get index in list for
     * @return  int     index of the specified element; -1 if element does not exist within the list
     */
    @Override
    public int indexOf(T element) {
        if (this.isEmpty()) {
            return -1;
        }

        Node<T> current = head;

        for (int i = 0; i < count; i++) {
            if (current.getElement().equals(element)) {
                return i;
            } else {
                current = current.getNext();
            }
        }

        return -1;
    }

    /**
     * Returns the first element in the list
     * @throws  NoSuchElementException  thrown if the list is currently emtpy
     * @return  T   the first element in the list
     */
    @Override
    public T first() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return head.getElement();
    }

    /**
     * Returns the last element in the list
     * @throws  NoSuchElementException   thrown if the list is currently empty
     * @return  T   the last element in the list
     */
    @Override
    public T last() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<T> current = head;
        T result = null;

        while (current != null) {
            if (current.getNext() == null) {
                result = current.getElement();
                break;
            } else {
                current = current.getNext();
            }
        }

        return result;
    }

    /**
     * Returns whether the specified target is contained within the list
     * @param   T   target  element to check whether list contains or not
     * @return  boolean     true if list contains target element, false if list does NOT contain target element
     */
    @Override
    public boolean contains(T target) {
        if (this.isEmpty()) {
            return false;
        }

        Node<T> current = head;

        while (current != null) {
            if (current.getElement().equals(target)) {
                return true;
            } else {
                current = current.getNext();
            }
        }

        return false;
    }

    /**
     * Returns whether the list is currently empty
     * @return  boolean     true if list is empty, false if list is not emtpy
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns the current size of the list
     * @return  int     current size of the list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Iterator to assist in tranversing through the list
     * @return  IUSingleLinkedListIterator
     */
    @Override
    public Iterator<T> iterator() {
        return new IUDoubleLinkedListIterator(this);
    }

    /**
     * Iterator sub class for our list iterator
     */
    private class IUDoubleLinkedListIterator implements Iterator<T> {

        /**
         * Iterator current index and iterator modification counter
         * @var int
         */
        private int current, iterModeCount;

        /**
         * Flag indicating whether its safe to remove an element from the list
         * @var boolean
         */
        private boolean canRemove;

        /**
         * Instance of the parent "outer" class
         * @var IUDoubleLinkedList<T>
         */
        private IUDoubleLinkedList<T> outer;

        /**
         * Class constructor that sets up the class properties
         * @param outer parent class instance
         */
        public IUDoubleLinkedListIterator(IUDoubleLinkedList<T> outer) {
            this.outer = outer;
            canRemove = false;
            iterModeCount = modCount;
            current = 0;
        }

        /**
         * Returns whether the list has another node
         * @throws  ConcurrentModificationException thrown in the case that the list was modified during the iterator execution
         * @return  boolean     true if list has another node, false if list has no more nodes
         */
        @Override
        public boolean hasNext() {
            if (iterModeCount != modCount) {
                throw new ConcurrentModificationException();
            }

            canRemove = false;

            return current < count;
        }

        /**
         * Returns the next element in the list
         * @return  T   next element in the list
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T item = get(current);
            current++;
            canRemove = true;
            return item;
        }
        
        /**
         * Remove an element from the list
         * @throws  ConcurrentModificationException thrown in the case that the list was modified during the iterator execution
         * @throws  IllegalStateException           thrown if can remove flag is currently false
         * @return  void
         */
        public void remove() {
            if (iterModeCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!canRemove) {
                throw new IllegalStateException();
            }

            outer.remove(current-1);
            iterModeCount++;
            canRemove = false;
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * toString method
     * Lists all current elements contained within the list
     * @return  String  list of all current elements in the list
     */
    public String toString() {
        String result = "[";
        Node<T> current = head;
        
        while (current != null) {
            result += (current.getPrevious() == null ? "" : ",") + current.getElement();
            current = current.getNext();
        }

        result += "]";

        return result;
    }
}
