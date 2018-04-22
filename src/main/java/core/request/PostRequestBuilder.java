package core.request;

import core.RequestType;

import java.net.URI;

/**
 * Created by akhil raj azhikodan on 22/4/18.
 */
public class PostRequestBuilder extends HttpRequestBuilderWithBody
{
    public PostRequestBuilder(URI uri) {
        this.requestType = RequestType.POST;
        this.uri = uri;
    }
}
