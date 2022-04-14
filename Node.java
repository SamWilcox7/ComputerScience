public class Node<T> {
    
    private Node<T> next;
    private T element;

    public Node() {
        next = null;
        element = null;
    }

    public Node(T element) {
        next = null;
        this.element = element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> node) {
        next = node;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public String toString() {
        return String.format("Element: %s has next: %s", element.toString(), (next != null));
    }
}
