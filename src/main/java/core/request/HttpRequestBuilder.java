package core.request;

import core.EntityType;
import core.RequestType;
import core.responsehandler.ResponseHandler;
import lombok.Getter;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static core.NetClient.DEFAULT_CONN_TIMEOUT;
import static core.NetClient.DEFAULT_SOCK_TIMEOUT;

/**
 * Created by akhil raj azhikodan on 22/4/18.
 */
public class HttpRequestBuilder
{
    RequestType requestType;
    URI uri;
    private int connTimeout = DEFAULT_CONN_TIMEOUT;
    private int socketTimeout = DEFAULT_SOCK_TIMEOUT;
    @Getter
    Object entity = new Object();
    @Getter
    EntityType entityType = EntityType.STRING;
    private ResponseHandler<?,?> handler;

    Map<String, String> headers = new HashMap<>();


    HttpRequestBuilder() {
    }

    public HttpRequestBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }


    public HttpRequestBuilder setConnTimeoutInSec(int timeout) {
        this.connTimeout = timeout * 1000;
        return this;
    }

    public HttpRequestBuilder setSocketTimeoutSec(int timeout) {
        this.socketTimeout = timeout * 1000;
        return this;
    }

    public HttpRequestBuilder setHandler(ResponseHandler<?,?> handler) {
        this.handler = handler;
        return this;
    }

    public HttpRequest build()
    {
        return new HttpRequest(requestType,
                               uri,
                               headers,
                               entityType,
                               Optional.of(entity),
                               connTimeout,
                               socketTimeout,
                               handler);
    }


}
