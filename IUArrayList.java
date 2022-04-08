import java.util.Arrays;
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

    private final static int DEFAULT_CAPACITY = 100;
    private T[] list;
    private int rear;

    public IUArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public IUArrayList(int initialCapacity) {
        rear = 0;
        list = (T[])(new Object[initialCapacity]);
    }

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

        this.rear++;
    }

    @Override
    public void addToRear(T element) {
        if (this.size() == this.list.length) {
            this.expandCapacity();
        }

        this.list[rear] = element;
        this.rear++;
    }

    @Override
    public void add(T element) {
        this.addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        int targetIdx = this.indexOf(target);

        if (targetIdx < 0) {
            throw new NoSuchElementException();
        }

        this.add(targetIdx+1, element);
    }

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
        this.rear++;
    }

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

        return result;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        T result = this.list[rear-1];

        this.list[rear-1] = null;
        this.rear--;

        return result;
    }

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
            return result;
        }
    }

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
        }

        return result;
    }

    @Override
    public void set(int index, T element) {
        if (this.isEmpty() || (index < 0 || index > rear - 1)) {
            throw new IndexOutOfBoundsException();
        }

        this.list[index] = element;
    }

    @Override
    public T get(int index) {
        if (this.isEmpty() || (index < 0 || index > rear - 1)) {
            throw new IndexOutOfBoundsException();
        }

        T result = this.list[index];
        
        return result;
    }

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

    @Override
    public T first() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.list[0];
    }

    @Override
    public T last() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.list[rear - 1];
    }

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
        Iterator<T> iterator = new Iterator<T>() {

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public T next() {
                // TODO Auto-generated method stub
                return null;
            }
            
        };

        return iterator;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        throw new UnsupportedOperationException();
    }

    private void expandCapacity() {
        this.list = Arrays.copyOf(this.list, this.list.length * 2);
    }
    
    public String toString() {
        String result = "[";

        for (int i = 0; i < this.rear; i++) {
            result += i == 0 ? i : ", " + i;
        }

        result += "]";

        return result;
    }
}
