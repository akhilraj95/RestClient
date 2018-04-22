package core.request;

import core.RequestType;

import java.net.URI;

/**
 * Created by akhil raj azhikodan on 22/4/18.
 */
public class GetRequestBuilder extends HttpRequestBuilder
{
    public GetRequestBuilder(URI uri) {
        this.requestType = RequestType.GET;
        this.uri = uri;
    }
}
