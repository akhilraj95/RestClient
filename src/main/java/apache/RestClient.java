package apache;

import com.google.gson.Gson;
import core.*;
import core.responsehandler.ResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/*
 * Created by akhil raj azhikodan on 14/4/18.
 */
public abstract class RestClient implements NetClient
{
    private static Gson gson = new Gson();

    private CloseableHttpClient getHttpClient(int connTimeout, int socketTimeout)
    {
        return HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                                                 .setSocketTimeout(socketTimeout)
                                                 .setConnectTimeout(connTimeout)
                                                 .build())
                .build();
    }

    private void postExecutionHook(CloseableHttpClient client) throws IOException
    {
        client.close();
    }

    private Response executeMethod(RequestBuilder requestBuilder,
                                   URI uri,
                                   Map<String, String> headers, int connTimeout, int sockTimeout) throws IOException
    {
        Response response;
        CloseableHttpClient client = getHttpClient(connTimeout, sockTimeout);

        Optional.ofNullable(headers).ifPresent(x -> x.forEach(requestBuilder::addHeader));
        requestBuilder.setUri(uri);

        try (CloseableHttpResponse httpResponse = client.execute(requestBuilder.build()))
        {
            HttpEntity entity = httpResponse.getEntity();

            String
                    responseString =
                    (Optional.ofNullable(entity).isPresent()) ? EntityUtils.toString(entity, "UTF-8") : null;
            Header[] responseHeaders = httpResponse.getAllHeaders();
            int statuscode = httpResponse.getStatusLine().getStatusCode();

            response = new Response(responseString, responseHeaders, statuscode);
            EntityUtils.consume(entity);
        }
        postExecutionHook(client);
        return response;
    }


    @Override
    public Response get(URI uri, Map<String, String> headers, int connTimeout, int sockTimeout) throws IOException
    {
        return executeMethod(RequestBuilder.get(), uri, headers, connTimeout, sockTimeout);
    }

    @Override
    public Response urlEncodedRequest(URI uri,
                                      RequestType type,
                                      Map<String, String> requestParams,
                                      Map<String, String> headers, int connTimeout, int sockTimeout)
            throws IOException
    {
        List<NameValuePair> params = requestParams.entrySet()
                .stream()
                .map(x -> new BasicNameValuePair(x.getKey(), x.getValue()))
                .collect(Collectors.toList());

        headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString());

        RequestBuilder requestBuilder = getRequestBuilder(type).setEntity(new UrlEncodedFormEntity(params));
        return executeMethod(requestBuilder, uri, headers, connTimeout, sockTimeout);
    }

    @Override
    public Response jsonRequest(URI uri,
                                RequestType type,
                                Object request,
                                Map<String, String> headers,
                                int connTimeout,
                                int sockTimeout)
            throws IOException
    {
        headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        RequestBuilder requestBuilder = getRequestBuilder(type)
                .setEntity(new ByteArrayEntity(gson.toJson(request).getBytes()));
        return executeMethod(requestBuilder, uri, headers, connTimeout, sockTimeout);
    }

    @Override
    public Response rawRequest(URI uri,
                               RequestType type,
                               String request,
                               Map<String, String> headers,
                               int connTimeout,
                               int sockTimeout)
            throws IOException
    {
        RequestBuilder requestBuilder = getRequestBuilder(type).setEntity(new ByteArrayEntity(request.getBytes()));
        return executeMethod(requestBuilder, uri, headers, connTimeout, sockTimeout);
    }


    RequestBuilder getRequestBuilder(RequestType type)
    {
        switch (type)
        {
            case POST:
                return RequestBuilder.post();
            case PUT:
                return RequestBuilder.put();
            case DELETE:
                return RequestBuilder.delete();
            default:
                return RequestBuilder.get();
        }
    }


    public Response call(HttpRequest request) throws IOException
    {

        if (request.getRequestType().equals(RequestType.GET))
        {
            return get(request.getUri(), request.getHeaders(), request.getConnTimeout(), request.getSocketTimout());
        }
        if (request.getEntityType().equals(EntityType.URLENCODED))
        {
            return urlEncodedRequest(request.getUri(),
                                     request.getRequestType(),
                                     (Map<String, String>) request.getEntity().get(),
                                     request.getHeaders(),
                                     request.getConnTimeout(),
                                     request.getSocketTimout());
        }
        else if (request.getEntityType().equals(EntityType.JSON))
        {
            return jsonRequest(request.getUri(),
                               request.getRequestType(),
                               request.getEntity(),
                               request.getHeaders(),
                               request.getConnTimeout(),
                               request.getSocketTimout());
        }
        else
        {
            return rawRequest(request.getUri(),
                              request.getRequestType(),
                              (String) request.getEntity().get(),
                              request.getHeaders(),
                              request.getConnTimeout(),
                              request.getSocketTimout());
        }
    }


}