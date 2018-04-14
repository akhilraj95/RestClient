package core.builder;

import com.sun.jndi.toolkit.url.Uri;
import core.EntityType;
import core.HttpRequest;
import core.RequestType;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */
public class HttpRequestBuilder
{
    private RequestType requestType;
    private URI uri;

    // default values
    Map<String, String> headers = new HashMap<>();
    Object entity = new Object();
    EntityType entityType = EntityType.STRING;

    public HttpRequestBuilder(RequestType requestType, URI uri)
    {
        this.requestType = requestType;
        this.uri = uri;
    }

    public HttpRequestBuilder headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }

    public HttpRequestBuilder jsonEntity(Object object)
    {
        this.entityType = EntityType.JSON;
        entity = object;
        return this;
    }

    public HttpRequestBuilder urlEncodedEntity(Object object)
    {
        this.entityType = EntityType.JSON;
        entity = object;
        return this;
    }

    public HttpRequestBuilder stringEntity(Object object)
    {
        this.entityType = EntityType.STRING;
        entity = object;
        return this;
    }

    public HttpRequest build()
    {
        return new HttpRequest(requestType, uri, headers, entityType, entity);
    }
}
