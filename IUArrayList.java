import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * ArrayList implementation of IndexedUnsortedList interface
 * 
 * @param <T> - type of elements held in this collection
 * @author The Boobies
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {

    private T[] list;
    private int rear;

    public IUArrayList(int initialCapacity) {
        rear = 0;
        list = (T[])(new Object[initialCapacity]);
    }

    @Override
    public void addToFront(T element) {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException("ArrayUnorderedList");
        }

        Comparable<T> comparableElement = (Comparable<T>)element;

        if (this.isEmpty()) {
            this.list[0] = element;
        } else {
            if (this.size() == this.list.length) {
                this.expandCapacity();
            }

            
        }
    }

    @Override
    public void addToRear(T element) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void add(T element) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addAfter(T element, T target) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public T removeFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T removeLast() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T remove(T element) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T remove(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void set(int index, T element) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int indexOf(T element) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public T first() {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("ArrayUnorderedList");
        }

        return this.list[0];
    }

    @Override
    public T last() {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("ArrayUnorderedList");
        }

        return this.list[rear - 1];
    }

    @Override
    public boolean contains(T target) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public int size() {
        return this.rear;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    private void expandCapacity() {
        this.list = Arrays.copyOf(this.list, this.list.length * 2);
    }
    
}
