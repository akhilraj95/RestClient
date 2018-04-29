package core.responsehandler;

import core.exception.ResponseHandlerException;
import core.response.Response;

public interface ResponseHandler<E, T> {
    Response<T> handle(E entity) throws ResponseHandlerException;
}
