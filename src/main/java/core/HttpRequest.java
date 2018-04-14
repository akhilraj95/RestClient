package core;


import java.net.URI;
import java.util.Map;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */

public class HttpRequest
{
    RequestType requestType;
    URI uri;
    Map<String, String> headers;
    EntityType entityType;
    Object entity;

    public HttpRequest(RequestType requestType,
                       URI uri,
                       Map<String, String> headers,
                       EntityType entityType,
                       Object entity)
    {
        this.requestType = requestType;
        this.uri = uri;
        this.headers = headers;
        this.entityType = entityType;
        this.entity = entity;
    }

    public RequestType getRequestType()
    {
        return requestType;
    }

    public URI getUri()
    {
        return uri;
    }

    public Map<String, String> getHeaders()
    {
        return headers;
    }

    public EntityType getEntityType()
    {
        return entityType;
    }

    public Object getEntity()
    {
        return entity;
    }
}
