public class Node<T> {
    
    private Node<T> next;
    private Node<T> previous;
    private T element;

    public Node() {
        next = previous = null;
        element = null;
    }

    public Node(T element) {
        next = null;
        previous = null;
        this.element = element;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setNext(Node<T> node) {
        next = node;
    }

    public void setPrevious(Node<T> node) {
        previous = node;
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
