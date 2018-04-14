package core.builder;

import core.RequestType;

import java.net.URI;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */
public class RequestBuilder
{
    public static HttpRequestBuilder get(URI uri) { return new HttpRequestBuilder(RequestType.GET, uri); }

    public static HttpRequestBuilder post(URI uri)
    {
        return new HttpRequestBuilder(RequestType.POST, uri);
    }

    public static HttpRequestBuilder delete(URI uri)
    {
        return new HttpRequestBuilder(RequestType.DELETE, uri);
    }

    public static HttpRequestBuilder put(URI uri)
    {
        return new HttpRequestBuilder(RequestType.PUT, uri);
    }

}
