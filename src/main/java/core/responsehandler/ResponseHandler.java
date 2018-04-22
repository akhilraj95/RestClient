package core.responsehandler;

import core.response.Response;

import java.io.IOException;

public interface ResponseHandler<E, T> {
    Response<T> handle(E entity) throws IOException;
}
