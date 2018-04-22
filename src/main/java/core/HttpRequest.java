package core;


import core.responsehandler.ResponseHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static core.NetClient.DEFAULT_CONN_TIMEOUT;
import static core.NetClient.DEFAULT_SOCK_TIMEOUT;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */

@AllArgsConstructor
@Getter
public class HttpRequest {
    private RequestType requestType;
    private URI uri;
    private Map<String, String> headers;
    private EntityType entityType;
    private Optional<Object> entity;
    private int connTimeout;
    private int socketTimout;
    private ResponseHandler<?,?> handler;

//    @NoArgsConstructor
//    public static class BasicHttpRequestBuilder {
//        RequestType requestType;
//        URI uri;
//        int connTimeout = DEFAULT_CONN_TIMEOUT;
//        int socketTimeout = DEFAULT_SOCK_TIMEOUT;
//
//         default values
//        Map<String, String> headers = new HashMap<>();
//
//        BasicHttpRequestBuilder(RequestType requestType, URI uri) {
//            this.requestType = requestType;
//            this.uri = uri;
//        }
//
//        public BasicHttpRequestBuilder headers(Map<String, String> headers) {
//            this.headers = headers;
//            return this;
//        }
//
//        public BasicHttpRequestBuilder setConnTimeoutInSec(int timeout) {
//            this.connTimeout = timeout * 1000;
//            return this;
//        }
//
//        public BasicHttpRequestBuilder setSocketTimeoutSec(int timeout) {
//            this.socketTimeout = timeout * 1000;
//            return this;
//        }
//
//        public Response execute() throws IOException {
//            return new RestClient().call(new HttpRequest(requestType, uri, headers, connTimeout, socketTimeout));
//        }
//
//        public Object executeWithHandler(ResponseHandler handler) throws Exception {
//            return handler.handle(new RestClient().call(new HttpRequest(requestType, uri, headers, connTimeout, socketTimeout)));
//        }
//    }

    public static class HttpRequestBuilder {
        RequestType requestType;
        URI uri;
        int connTimeout = DEFAULT_CONN_TIMEOUT;
        int socketTimeout = DEFAULT_SOCK_TIMEOUT;
        @Getter
        Object entity = new Object();
        @Getter
        EntityType entityType = EntityType.STRING;
        private ResponseHandler<?,?> handler;
//         default values
        Map<String, String> headers = new HashMap<>();


        public HttpRequestBuilder() {
        }

        public HttpRequestBuilder get(URI uri) {
            this.requestType = RequestType.GET;
            this.uri = uri;
            return this;
        }

        public HttpRequestBuilder post(URI uri) {
            this.requestType = RequestType.POST;
            this.uri = uri;
            return this;
        }

        public HttpRequestBuilder delete(URI uri) {
            this.requestType = RequestType.DELETE;
            this.uri = uri;
            return this;
        }

        public HttpRequestBuilder put(URI uri) {
            this.requestType = RequestType.PUT;
            this.uri = uri;
            return this;
        }

        public HttpRequestBuilder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public HttpRequestBuilder jsonEntity(Object object) {
            this.entityType = EntityType.JSON;
            entity = object;
            return this;
        }

        public HttpRequestBuilder urlEncodedEntity(Object object) {
            this.entityType = EntityType.URLENCODED;
            entity = object;
            return this;
        }

        public HttpRequestBuilder stringEntity(Object object) {
            this.entityType = EntityType.STRING;
            entity = object;
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

        public HttpRequest build() throws IOException {
            return new HttpRequest(requestType,
                    uri,
                    headers,
                    entityType,
                    Optional.of(entity),
                    connTimeout,
                    socketTimeout,
                    handler);
        }

//        public Object executeWithHandler(ResponseHandler handler) throws Exception
//        {
//            return handler.handle(new RestClient().call(new HttpRequest(requestType,
//                    uri,
//                    headers,
//                    entityType,
//                    Optional.of(entity),
//                    connTimeout,
//                    socketTimeout)));
//        }

    }

}
