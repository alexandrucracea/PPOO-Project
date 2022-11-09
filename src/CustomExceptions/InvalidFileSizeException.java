package CustomExceptions;

public class InvalidFileSizeException extends Exception{
    public InvalidFileSizeException(String errorMessage) {
        super(errorMessage);
    }
}
