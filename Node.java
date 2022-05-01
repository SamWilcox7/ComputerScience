/**
 * Bi-Directional Node class
 * Best if used for doubly-linked lists
 * @author The Boobies
 */
public class Node<T> {
    
    /**
     * Next node object instance
     * @var Node<T>
     */
    private Node<T> next;

    /**
     * Previous node object instance
     * @var Node<T>
     */
    private Node<T> previous;

    /**
     * Element value of current element
     * @var T
     */
    private T element;

    /**
     * Class constructor that creates empty nodes
     */
    public Node() {
        next = previous = null;
        element = null;
    }

    /**
     * Class constructor that creates empty nodes but populates the elment
     * @param element   element value to set initially
     */
    public Node(T element) {
        next = null;
        previous = null;
        this.element = element;
    }

    /**
     * Returns the next node in the collection
     * @return  Node<T>
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Returns the previous node in the collection
     * @return  Node<T>
     */
    public Node<T> getPrevious() {
        return previous;
    }

    /**
     * Sets the next node in the collection to the given node
     * @param node  node to set as the next node
     */
    public void setNext(Node<T> node) {
        next = node;
    }

    /**
     * Sets the previous node in the collection to the given node
     * @param node  node to set as the previous node
     */
    public void setPrevious(Node<T> node) {
        previous = node;
    }

    /**
     * Returns the current element value
     * @return  T
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the current element value
     * @param element   element value to set as current element value
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Usual toString to display the current state of this class
     * @return  String
     */
    public String toString() {
        return String.format("Element: %s. Has previous: %s. Has next: %s", element.toString(), (previous != null), (next != null));
    }
}
