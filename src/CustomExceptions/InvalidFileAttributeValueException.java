package CustomExceptions;

public class InvalidFileAttributeValueException extends Exception{
    public InvalidFileAttributeValueException(String errorMessage) {
        super(errorMessage);
    }
}
