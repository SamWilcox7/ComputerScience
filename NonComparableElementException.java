public class NonComparableElementException extends RuntimeException {
    
    public NonComparableElementException(String collection) {
        super("The " + collection + " does not contain comparable elements.");
    }
}
