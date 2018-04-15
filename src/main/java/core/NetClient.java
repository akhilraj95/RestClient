package core;

import core.builder.HttpRequestBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */
public interface NetClient
{
    Response get(URI uri, Map<String, String> headers) throws IOException;
    Response urlEncodedRequest(URI uri, RequestType type, Map<String, String> requestParams, Map<String, String> headers)
            throws IOException;
    Response jsonRequest(URI uri, RequestType type, Object request, Map<String, String> headers)
            throws IOException;
    Response rawRequest(URI uri, RequestType type, String request, Map<String, String> headers) throws IOException;
}