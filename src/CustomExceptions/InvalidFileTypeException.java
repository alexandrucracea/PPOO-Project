package CustomExceptions;

public class InvalidFileTypeException extends Exception{
    public InvalidFileTypeException(String errorMessage) {
        super(errorMessage);
    }
}
