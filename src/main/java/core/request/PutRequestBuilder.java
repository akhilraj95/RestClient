package core.request;

import core.RequestType;

import java.net.URI;

/**
 * Created by akhil raj azhikodan on 22/4/18.
 */
public class PutRequestBuilder extends HttpRequestBuilderWithBody
{
    public PutRequestBuilder(URI uri) {
        this.requestType = RequestType.PUT;
        this.uri = uri;
    }

}
