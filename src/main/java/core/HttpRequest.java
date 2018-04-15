package core;


import lombok.Getter;

import java.net.URI;
import java.util.Map;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */

@Getter
public class HttpRequest
{
    RequestType requestType;
    URI uri;
    Map<String, String> headers;
    EntityType entityType;
    Object entity;

    int connTimeout;
    int socketTimout;


    public HttpRequest(RequestType requestType,
                       URI uri,
                       Map<String, String> headers,
                       EntityType entityType,
                       Object entity,
                       int connTimeout,
                       int socketTimout)
    {
        this.requestType = requestType;
        this.uri = uri;
        this.headers = headers;
        this.entityType = entityType;
        this.entity = entity;
        this.connTimeout = connTimeout;
        this.socketTimout = socketTimout;
    }

    public HttpRequest(RequestType requestType,
                       URI uri,
                       Map<String, String> headers,
                       int connTimeout,
                       int socketTimout)
    {
        this.requestType = requestType;
        this.uri = uri;
        this.headers = headers;
        this.entity = null;
        this.entityType = EntityType.NONE;
        this.connTimeout = connTimeout;
        this.socketTimout = socketTimout;
    }
}
