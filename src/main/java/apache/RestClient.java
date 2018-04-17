package apache;

import com.google.gson.Gson;
import core.*;
import core.HttpRequest;
import core.responsehandler.ResponseHandler;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/*
 * Created by akhil raj azhikodan on 14/4/18.
 */
public abstract class RestClient implements NetClient {
    public final static int CONECTION_TIMEOUT = 10000;
    public final static int SOCKET_TIMEOUT = 10000;
    private static Gson gson = new Gson();
    private final CloseableHttpClient client;
    private final ResponseHandler<HttpResponse, String> defaultHandler = new StringHandler<>();

    public RestClient() {
        this.client = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setSocketTimeout(CONECTION_TIMEOUT)
                        .setConnectTimeout(SOCKET_TIMEOUT)
                        .build())
                .build();
    }


    @Override
    protected void finalize() throws Throwable {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Response<?> executeMethod(RequestBuilder requestBuilder, Optional<ResponseHandler<HttpResponse, ?>> handler) throws IOException {
        try (CloseableHttpResponse httpResponse = client.execute(requestBuilder.build())) {
            if(handler.isPresent())
                return handler.get().handle(httpResponse);
            return defaultHandler.handle(httpResponse);
        }
    }


    private Response get(URI uri, Map<String, String> headers, int connTimeout, int sockTimeout, Optional<ResponseHandler<HttpResponse,?>> handler) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(sockTimeout).setSocketTimeout(connTimeout).build();
        RequestBuilder requestBuilder = RequestBuilder.get().setConfig(requestConfig).setUri(uri);
        headers.forEach(requestBuilder::addHeader);
        return executeMethod(requestBuilder, handler);
    }

    private Response urlEncodedRequest(URI uri,
                                      RequestType type,
                                      Map<String, String> requestParams,
                                      Map<String, String> headers, int connTimeout, int sockTimeout,
                                      Optional<ResponseHandler<HttpResponse,?>> handler)
            throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(sockTimeout).setSocketTimeout(connTimeout).build();
        List<NameValuePair> params = requestParams.entrySet()
                .stream()
                .map(x -> new BasicNameValuePair(x.getKey(), x.getValue()))
                .collect(Collectors.toList());

        headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString());

        RequestBuilder requestBuilder = getRequestBuilder(type).setEntity(new UrlEncodedFormEntity(params)).setConfig(requestConfig).setUri(uri);
        addHeader(requestBuilder, headers);
        return executeMethod(requestBuilder, handler);
    }

    private Response jsonRequest(URI uri,
                                RequestType type,
                                Object request,
                                Map<String, String> headers,
                                int connTimeout,
                                int sockTimeout,
                                Optional<ResponseHandler<HttpResponse,?>> handler)
            throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(sockTimeout).setSocketTimeout(connTimeout).build();
        headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        RequestBuilder requestBuilder = getRequestBuilder(type)
                .setEntity(new ByteArrayEntity(gson.toJson(request).getBytes())).setConfig(requestConfig).setUri(uri);
        addHeader(requestBuilder, headers);
        return executeMethod(requestBuilder, handler);
    }

    private Response rawRequest(URI uri,
                               RequestType type,
                               String request,
                               Map<String, String> headers,
                               int connTimeout,
                               int sockTimeout,
                               Optional<ResponseHandler<HttpResponse,?>> handler)
            throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(sockTimeout).setSocketTimeout(connTimeout).build();
        RequestBuilder requestBuilder = getRequestBuilder(type).setEntity(new ByteArrayEntity(request.getBytes())).setConfig(requestConfig).setUri(uri);
        addHeader(requestBuilder, headers);
        return executeMethod(requestBuilder, handler);
    }


    RequestBuilder getRequestBuilder(RequestType type) {
        switch (type) {
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


    @Override
    public Response call(HttpRequest request) throws IOException {

        if (request.getRequestType().equals(RequestType.GET)) {
            return get(request.getUri(), request.getHeaders(), request.getConnTimeout(), request.getSocketTimout(),
                    Optional.ofNullable((ResponseHandler<HttpResponse, ?>) request.getHandler()));
        }
        if (request.getEntityType().equals(EntityType.URLENCODED)) {
            return urlEncodedRequest(request.getUri(),
                    request.getRequestType(),
                    (Map<String, String>) request.getEntity().get(),
                    request.getHeaders(),
                    request.getConnTimeout(),
                    request.getSocketTimout(),
                    Optional.ofNullable((ResponseHandler<HttpResponse, ?>) request.getHandler()));
        } else if (request.getEntityType().equals(EntityType.JSON)) {
            return jsonRequest(request.getUri(),
                    request.getRequestType(),
                    request.getEntity(),
                    request.getHeaders(),
                    request.getConnTimeout(),
                    request.getSocketTimout(),
                    Optional.ofNullable((ResponseHandler<HttpResponse, ?>) request.getHandler()));
        } else {
            return rawRequest(request.getUri(),
                    request.getRequestType(),
                    (String) request.getEntity().get(),
                    request.getHeaders(),
                    request.getConnTimeout(),
                    request.getSocketTimout(),
                    Optional.ofNullable((ResponseHandler<HttpResponse, ?>) request.getHandler()));
        }
    }

    private void addHeader(RequestBuilder requestBuilder, Map<String,String> headers) {
        headers.forEach(requestBuilder::addHeader);
    }


}