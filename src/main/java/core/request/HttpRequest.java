package core.request;


import com.sun.jndi.toolkit.url.Uri;
import core.EntityType;
import core.RequestType;
import core.responsehandler.ResponseHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */

@AllArgsConstructor(access = AccessLevel.MODULE)
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

    public static GetRequestBuilder get(URI uri)
    {
        return new GetRequestBuilder(uri);
    }

    public static PostRequestBuilder post(URI uri)
    {
        return new PostRequestBuilder(uri);
    }

    public static PutRequestBuilder put(URI uri)
    {
        return new PutRequestBuilder(uri);
    }

    public static DeleteRequestBuilder delete(URI uri)
    {
        return new DeleteRequestBuilder(uri);
    }
}
