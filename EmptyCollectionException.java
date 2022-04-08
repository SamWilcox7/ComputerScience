public class EmptyCollectionException extends RuntimeException {
    
    public EmptyCollectionException(String message) {
        super("Collection " + message + " is empty");
    }
}
