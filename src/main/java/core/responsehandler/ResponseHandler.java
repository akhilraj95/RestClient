package core.responsehandler;

import apache.ResponseHandlerException;
import core.response.Response;

public interface ResponseHandler<E, T> {
    Response<T> handle(E entity) throws ResponseHandlerException;
}
