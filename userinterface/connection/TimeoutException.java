package userinterface.connection;

public class TimeoutException extends RuntimeException {
    public TimeoutException() {
        super("Timeout");
    }
}