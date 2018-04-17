package apache;

public class ResponseHandlerException extends RuntimeException{
    public ResponseHandlerException() {
    }

    public ResponseHandlerException(String message) {
        super(message);
    }

    public ResponseHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseHandlerException(Throwable cause) {
        super(cause);
    }

    public ResponseHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
