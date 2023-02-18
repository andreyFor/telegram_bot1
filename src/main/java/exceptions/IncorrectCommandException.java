package exceptions;

public class IncorrectCommandException extends RuntimeException {
    public IncorrectCommandException() {
        super();
    }

    public IncorrectCommandException(String message) {
        super(message);
    }

    public IncorrectCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCommandException(Throwable cause) {
        super(cause);
    }

    protected IncorrectCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}