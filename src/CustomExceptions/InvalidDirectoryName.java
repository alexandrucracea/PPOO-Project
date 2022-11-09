package CustomExceptions;

public class InvalidDirectoryName extends Exception{
    public InvalidDirectoryName(String message) {
        super(message);
    }
}
