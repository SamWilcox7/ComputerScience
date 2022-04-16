import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * ArrayList implementation of IndexedUnsortedList interface
 * 
 * @param <T> - type of elements held in this collection
 * @author The Boobies
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {

    /**
     * Initial capacity for the list array
     * @var int
     */
    private final static int DEFAULT_CAPACITY = 100;

    /**
     * Array list collection for generics
     * @var T array
     */
    private T[] list;

    /**
     * Both rear and modification counters
     * @var int
     */
    private int rear, modCount;

    /**
     * Class constructor taking no paramaters
     */
    public IUArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Class constructor taking parameters
     * @param initialCapacity   the initial capacity for the list array
     */
    public IUArrayList(int initialCapacity) {
        rear = modCount = 0;
        list = (T[])(new Object[initialCapacity]);
    }

    /**
     * Adds an item to the front of the list
     * @param   T   element     element to add to the front of the list
     */
    @Override
    public void addToFront(T element) {
        if (this.isEmpty()) {
            this.list[0] = element;
        } else {
            if (this.size() == this.list.length) {
                this.expandCapacity();
            }

            for (int shift = rear-1; shift > 0; shift--) {
                this.list[shift] = this.list[shift-1];
            }

            this.list[0] = element;
        }

        this.modCount++;
        this.rear++;
    }

    /**
     * Adds an item to the rear of the list
     * @param   T   element     element to add to the rear of the list
     */
    @Override
    public void addToRear(T element) {
        if (this.size() == this.list.length) {
            this.expandCapacity();
        }

        this.list[rear] = element;
        this.modCount++;
        this.rear++;
    }

    /**
     * Adds an item to the array
     * This will add the item to the rear of the list
     * @param   T   element     the element to add to the list
     */
    @Override
    public void add(T element) {
        this.addToRear(element);
    }

    /**
     * Adds an element after the given target element in the list
     * @param   T   element     element to add after target element
     * @param   T   target      target element
     * @throws  NoSuchElementException  thrown when no element exists for the given element
     */
    @Override
    public void addAfter(T element, T target) {
        int targetIdx = this.indexOf(target);

        if (targetIdx < 0) {
            throw new NoSuchElementException();
        }

        this.add(targetIdx+1, element);
    }

    /**
     * Adds an element at the given index of the list
     * @param   int     index   index at which to add element
     * @param   T       element element to add at the given index
     * @throws  IndexOutOfBoundsException   thrown when the index is out of range
     */
    @Override
    public void add(int index, T element) {
        if (this.size() == this.list.length) {
            this.expandCapacity();
        }

        if (index < 0 || index > rear) {
            throw new IndexOutOfBoundsException();
        }

        for (int shift = rear; shift > index; shift--) {
            this.list[shift] = this.list[shift-1];
        }

        this.list[index] = element;
        this.modCount++;
        this.rear++;
    }

    /**
     * Removes the first element from the list and also returns that element
     * @throws  NoSuchElementException  thrown if the list is currently empty
     * @return  T   element removed from the list
     */
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        T result = this.list[0];

        for (int shift = 0; shift < rear-1; shift++) {
            this.list[shift] = this.list[shift+1];
        }

        this.list[rear-1] = null;
        this.rear--;
        this.modCount++;

        return result;
    }

    /**
     * Removes the last element from the list and also returns that element
     * @throws  NoSuchElementException  thrown if the list is currently empty
     * @return  T   element removed from the list
     */
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        System.out.println("REAR: " + rear + " :: AFTER MINUS 1: " + (rear-1));
        System.out.println("NULL, BUT ITS THIS -> " + this.list[rear-1]);

        T result = this.list[rear-1];

        this.list[rear-1] = null;
        this.rear--;
        this.modCount++;

        return result;
    }

    /**
     * Removes the given element from the list and also returns the removed item
     * @param   T   element     element to remove from the list
     * @throws  NoSuchElementException  thrown if the list is currently empty or element is not found
     * @return  T   element removed from the list
     */
    @Override
    public T remove(T element) {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        T[] temp = (T[])(new Object[rear]);
        boolean exists = false;
        T result = null;

        for (int i = 0, j = 0; i < this.rear; i++) {
            if (this.list[i] != element) {
                temp[j++] = this.list[i];
            } else {
                exists = true;
                result = this.list[i];
            }
        }

        if (!exists) {
            throw new NoSuchElementException();
        } else {
            this.list = temp;
            this.rear--;
            this.modCount++;
            return result;
        }
    }

    /**
     * Removes an item from the list at the given index and also returns the removed element
     * @param   int     index   index at which to remove an element
     * @throws  IndexOutOfBoundsException   thrown is the given index is out of range
     * @return  T   element removed from the list
     */
    @Override
    public T remove(int index) {
        if (this.isEmpty() || (index < 0 || index > rear - 1)) {
            throw new IndexOutOfBoundsException();
        }

        T result = this.list[index];

        if ((rear - 1) == 0) {
            this.list = (T[])(new Object[DEFAULT_CAPACITY]);
            this.rear = 0;
        } else {
            T[] temp = (T[])(new Object[rear-1]);

            for (int i = 0, j = 0; i < this.list.length; i++) {
                if (i != index) {
                    temp[j++] = this.list[i];
                }
            }

            this.list = temp;
            this.rear--;
            this.modCount++;
        }

        return result;
    }

    /**
     * Sets an existing element in the array at a given index with a new element
     * @param   int     index   index at which to set element
     * @param   T       element element to set at the given index
     * @throws  IndexOutOfBoundsException   thrown if the given index is out of range
     */
    @Override
    public void set(int index, T element) {
        if (this.isEmpty() || (index < 0 || index > rear - 1)) {
            throw new IndexOutOfBoundsException();
        }

        this.list[index] = element;
        this.modCount++;
    }

    /**
     * Gets an element at the given index of the list
     * @param   int     index   index at which to get an element
     * @throws  IndexOutOfBoundsException   thrown if the given index is out of range
     * @return  T   element at given index
     */
    @Override
    public T get(int index) {
        if (this.isEmpty() || (index < 0 || index > rear - 1)) {
            throw new IndexOutOfBoundsException();
        }

        T result = this.list[index];
        
        return result;
    }

    /**
     * Returns the index for the given element in the list
     * If no element is found, then this method will return -1
     * @param   T   element     element to get index for in the list
     * @return  int (-1 if element not found or list is empty)
     */
    @Override
    public int indexOf(T element) {
        if (this.isEmpty()) {
            return -1;
        }

        int result = -1;

        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i] == element) {
                result = i;
                break;
            }
        }

        return result;
    }

    /**
     * Returns the first element from the list
     * @throws  NoSuchElementException  thrown if the list is currently empty
     * @return  T   first element
     */
    @Override
    public T first() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.list[0];
    }

    /**
     * Returns the last element from the list
     * @throws  NoSuchElementException thrown if the list is currently empty
     * @return  T   last element
     */
    @Override
    public T last() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.list[rear - 1];
    }

    /**
     * Returns whether the list contains the given target element
     * @param   T   target  target element to check if exists in the list
     * @return  boolean     true if found, false if not found
     */
    @Override
    public boolean contains(T target) {
        if (this.isEmpty()) {
            return false;
        }

        boolean exists = false;

        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i] == target) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    /**
     * Returns whether the list is currently empty
     * @return  boolean     true if empty, false if not empty
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns the current size of the list
     * @return  int     current size
     */
    @Override
    public int size() {
        return this.rear;
    }

    /**
     * Iterator to help with traversing through the list
     * @return  IUArrayListIterator
     */
    @Override
    public Iterator<T> iterator() {
        return new IUArrayListIterator(this);
    }

    /**
     * Iterator sub class
     */
    private class IUArrayListIterator implements Iterator<T> {

        /**
         * Current and iterator modification counters
         * @var int
         */
        private int current, iterModCount;

        /**
         * Boolean flag to indicate if an element can be removed
         * @var boolean
         */
        private boolean canRemove;

        /**
         * Instance of the parent "outer" class
         * @var IUArrayList<T>
         */
        private IUArrayList<T> outer;

        /**
         * Class constructor
         * @param outer IUArrayList<T>  parent class
         */
        public IUArrayListIterator(IUArrayList<T> outer) {
            this.outer = outer;
            current = 0;
            canRemove = false;
            iterModCount = modCount;
        }

        /**
         * Returns whether the list has another element
         * @throws  ConcurrentModificationException thrown if the list was modified during iterator execution
         * @return  boolean     true if the list has another element, false if list does not have another element
         */
        @Override
        public boolean hasNext() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            canRemove = false;

            return current < rear;
        }

        /**
         * Returns the next element in the list
         * @throws NoSuchElementException   thrown if there is not a next element in the list
         * @return  T   the next element
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T item = list[current];
            current++;
            canRemove = true;

            return item;
        }

        /**
         * Removes the last element from the next method from the list
         * @throws  ConcurrentModificationException thrown if the list was modified during iterator execution
         * @throws  IllegalStateException           thrown if the boolean remove flag is false
         */
        public void remove() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!canRemove) {
                throw new IllegalStateException();
            }

            outer.remove(current-1);
            iterModCount++;
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
     * Doubles the capacity of the array
     */
    private void expandCapacity() {
        this.list = Arrays.copyOf(this.list, this.list.length * 2);
    }
    
    /**
     * toString method
     * Lists all current elements contained within the list
     * @return  String  list of all current elements in the list
     */
    public String toString() {
        String result = "[";

        for (int i = 0; i < this.rear; i++) {
            result += i == 0 ? this.list[i] : ", " + this.list[i];
        }

        result += "]";

        return result;
    }
}
