package core.responsehandler;

import apache.ResponseHandlerException;
import core.response.Response;

import java.io.IOException;

public interface ResponseHandler<E, T> {
    Response<T> handle(E entity) throws ResponseHandlerException;
}
