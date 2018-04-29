package apache;

import java.io.IOException;

public class ResponseHandlerException extends IOException {
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

}
